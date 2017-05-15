package eu.h2020.symbiote.security.rest.clients;

import eu.h2020.symbiote.security.SecurityHandler;
import eu.h2020.symbiote.security.certificate.Certificate;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.enums.ValidationStatus;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.rest.AAMRESTInterface;
import eu.h2020.symbiote.security.session.AAM;
import eu.h2020.symbiote.security.token.Token;
import feign.Feign;
import feign.Response;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Client for Symbiote's AAMs' services.
 * <p>
 * To learn about available entry points fetch them using {@link SecurityHandler#getAvailableAAMs()}
 *
 * @author Elena Garrido (Atos)
 * @author Mikołaj Dobski (PSNC)
 */
public class AAMClient {

    private static final Log logger = LogFactory.getLog(AAMClient.class);
    private static final String errorMessage = "Error accessing to AAM server at ";
    private AAMRESTInterface simpleclient;
    private AAMRESTInterface jsonclient;
    private String url;

    /**
     * For the use of specific implementations
     */
    protected AAMClient() {
    }

    /**
     * Used to create the symbiote entry point to selected AAMs
     *
     * @param platformAAM to learn about available platform AAMs fetch them using {@link SecurityHandler#getAvailableAAMs()} from the Core AAM
     */
    public AAMClient(AAM platformAAM) {
        this.createClient(platformAAM.getAamAddress());
    }

    protected void createClient(String url) {
        this.url = url;
        simpleclient = Feign.builder().target(AAMRESTInterface.class, url);
        jsonclient = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).target
                (AAMRESTInterface.class, url);
    }

    public String getURL() {
        return url;
    }

    private String getAAMRootCertificatePEMString() {
        String result = null;
        try {
            result = simpleclient.getRootCertificate();
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public X509Certificate getAAMRootCertificate() throws CertificateException {
        String pemCert = getAAMRootCertificatePEMString();
        if (pemCert == null || pemCert.isEmpty())
            return null;
        return new Certificate(pemCert).getX509();
    }

    public Token login(Credentials credential) {
        Token result = null;
        try {
            logger.info("User trying to login " + credential.getUsername() + " - " + credential.getPassword());
            result = new Token(jsonclient.login(credential).headers().get(AAMConstants.TOKEN_HEADER_NAME).iterator()
                    .next());
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public ValidationStatus checkTokenRevocation(Token token) {
        ValidationStatus result = null;
        try {
            logger.info("User trying to checkTokenRevocation");
            result = ValidationStatus.valueOf(jsonclient.checkTokenRevocation(token.toString()).getStatus());
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public Token requestFederatedCoreToken(Token token) {
        Token result = null;
        try {
            logger.info("User trying to requestFederatedCoreToken");
            Response response = jsonclient.requestForeignToken(token.toString());
            result = new Token(response.headers().get(AAMConstants.TOKEN_HEADER_NAME).iterator().next());
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public Token requestForeignToken(Token token) {
        Token result = null;
        try {
            logger.info("User trying to requestForeignToken");
            result = new Token(jsonclient.requestForeignToken(token.toString()).headers().get(AAMConstants
                    .TOKEN_HEADER_NAME).iterator().next());
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

}

