package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.interfaces.*;
import eu.h2020.symbiote.security.ClientSecurityHandlerFactory;
import eu.h2020.symbiote.security.commons.credentials.BoundCredentials;
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
import java.util.Set;

/**
 * Factory for creating Feign symbIoTe clients
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeFeignClientFactory extends AbstractSymbIoTeClientFactory {

    private static final Log logger = LogFactory.getLog(SymbIoTeFeignClientFactory.class);

    public static final String HOME_PLATFORM_IDS_HEADER = "homePlatformIds";
    public static final String SERVER_VALIDATION_HEADER = "serverValidation";

    private final ISecurityHandler securityHandler;
    private final String coreAddress;

    /**
     *
     * @param config the configuration
     * @throws SecurityHandlerException on creation error (e.g. problem with the wallet)
     * @throws NoSuchAlgorithmException
     */
    public SymbIoTeFeignClientFactory(Config config)
            throws SecurityHandlerException, NoSuchAlgorithmException {

        this.coreAddress = config.getCoreAddress();

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
    public SearchClient getSearchClient() { return new FeignSearchClient(securityHandler, coreAddress); }

    @Override
    public CRAMClient getCramClient() { return new FeignCRAMClient(securityHandler, coreAddress); }

    @Override
    public RHClient getRHClient(String platformId) { return new FeignRHClient(securityHandler, platformId); }

    @Override
    public RAPClient getRapClient() { return new FeignRAPClient(securityHandler); }

    @Override
    public PRClient getPRClient(String platformId) { return new FeignPRClient(securityHandler, platformId); }

    @Override
    public SMClient getSMClient(String platformId) { return new FeignSMClient(securityHandler, platformId); }

    @Override
    public IAAMClient getAAMClient(String homePlatformId) {
        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();
            return new AAMClient(availableAAMs.get(homePlatformId).getAamAddress());
        } catch (SecurityHandlerException e) {
            logger.error("Could not create AAMClient", e);
        }
        return null;
    }

    @Override
    public void initializeInHomePlatforms(Set<HomePlatformCredentials> credentials) throws SecurityHandlerException {

        // Get the available AAMs from the Core AAM
        Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();
        Map<String, BoundCredentials> credentialsMap = securityHandler.getAcquiredCredentials();

        for (HomePlatformCredentials cred : credentials) {

            // Acquiring application certificate if not present
            if (!credentialsMap.containsKey(cred.getPlatformId()))
                securityHandler.getCertificate(
                        availableAAMs.get(cred.getPlatformId()),
                        cred.getUsername(),
                        cred.getPassword(),
                        cred.getClientId());
        }
    }
}
