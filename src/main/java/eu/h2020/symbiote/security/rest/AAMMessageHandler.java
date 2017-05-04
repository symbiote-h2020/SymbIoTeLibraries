package eu.h2020.symbiote.security.rest;

import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public abstract class AAMMessageHandler {

    private static final Log logger = LogFactory.getLog(AAMMessageHandler.class);
    private static final String errorMessage = "Error accessing to AAM server at ";
    private AAMRestService simpleclient;
    private AAMRestService jsonclient;
    private String url;

    public AAMMessageHandler() {
    }


    public void createClient(String url) {
        this.url = url;
        simpleclient = Feign.builder().target(AAMRestService.class, url);
        jsonclient = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).target(AAMRestService.class, url);
    }

    public String getURL() {
        return url;
    }

    private byte[] getAAMRootCertificateAsByteArray() {
        byte[] result = null;
        try {
            result = simpleclient.getRootCertificate();
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public X509Certificate getAAMRootCertificate() throws CertificateException {
        byte[] keyBytes = getAAMRootCertificateAsByteArray();
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        if (keyBytes != null)
            return (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(keyBytes));
        return null;
    }

    public Token login(Credentials credential) {
        Token result = null;
        try {
            logger.info("User trying to login " + credential.getUsername() + " - " + credential.getPassword());
            result = jsonclient.login(credential);
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public TokenValidationStatus checkTokenRevocation(Token token) {
        TokenValidationStatus result = null;
        try {
            logger.info("User trying to checkTokenRevocation");
            result = jsonclient.checkTokenRevocation(token);
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public Token requestCoreToken(Token token) {
        Token result = null;
        try {
            logger.info("User trying to requestCoreToken");
            result = jsonclient.requestCoreToken(token);
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

    public Token requestForeignToken(Token token) {
        Token result = null;
        try {
            logger.info("User trying to requestForeignToken");
            result = jsonclient.requestForeignToken(token);
        } catch (Exception e) {
            logger.error(errorMessage + url, e);
        }
        return result;
    }

}

