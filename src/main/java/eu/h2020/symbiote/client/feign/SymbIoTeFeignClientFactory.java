package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.*;
import eu.h2020.symbiote.security.ClientSecurityHandlerFactory;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.AAMClient;
import eu.h2020.symbiote.security.communication.IAAMClient;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Factory for creating Feign symbIoTe clients
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeFeignClientFactory extends AbstractSymbIoTeClientFactory {

    private static final Log logger = LogFactory.getLog(SymbIoTeFeignClientFactory.class);

    private final ISecurityHandler securityHandler;
    private final String coreAddress;
    private final String homePlatformId;
    private final String username;
    private final String password;
    private final String clientId;

    /**
     *
     * @param config the configuration
     * @throws SecurityHandlerException on creation error (e.g. problem with the wallet)
     * @throws NoSuchAlgorithmException
     */
    public SymbIoTeFeignClientFactory(Config config)
            throws SecurityHandlerException, NoSuchAlgorithmException {

        this.coreAddress = config.getCoreAddress();
        this.homePlatformId = config.getHomePlatformId();
        this.username = config.getUsername();
        this.password = config.getPassword();
        this.clientId = config.getClientId();

        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        this.securityHandler = ClientSecurityHandlerFactory
                .getSecurityHandler(
                        config.getCoreAddress() + CORE_AAM_SUBPATH,
                        config.getKeystorePath(),
                        config.getKeystorePassword()
                );
    }

    @Override
    public SearchClient getSearchClient() {
        return new FeignSearchClient(securityHandler, coreAddress, homePlatformId, username, password, clientId);
    }

    @Override
    public CRAMClient getCramClient() {
        return new FeignCRAMClient(securityHandler, coreAddress, homePlatformId, username, password, clientId);
    }

    @Override
    public RHClient getRHClient() {
        return new FeignRHClient(securityHandler, homePlatformId);
    }

    @Override
    public RAPClient getRapClient() {
        return new FeignRAPClient(securityHandler, homePlatformId, username, password, clientId);
    }

    @Override
    public PRClient getPRClient() {
        return new FeignPRClient(securityHandler, homePlatformId, username, password, clientId);
    }

    @Override
    public IAAMClient getAAMClient() {
        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();
            return new AAMClient(availableAAMs.get(homePlatformId).getAamAddress());
        } catch (SecurityHandlerException e) {
            logger.error("Could not create AAMClient", e);
        }
        return null;
    }
}
