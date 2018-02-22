package eu.h2020.symbiote.client;

import eu.h2020.symbiote.security.ComponentSecurityHandlerFactory;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.SymbioteAuthorizationClient;
import eu.h2020.symbiote.security.handler.IComponentSecurityHandler;
import feign.Client;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SymbioteComponentClientFactory {

    private static final Log logger = LogFactory.getLog(SymbioteComponentClientFactory.class);

    public static class SecurityConfiguration {
        private String keystorePath;
        private String keystorePassword;
        private String clientId;
        private String platformId;
        private String componentId;
        private String localAAMAddress;
        private String componentOwnerUsername;
        private String componentOwnerPassword;

        public SecurityConfiguration(String keystorePath, String keystorePassword, String clientId, String platformId,
                                     String componentId, String localAAMAddress, String componentOwnerUsername,
                                     String componentOwnerPassword) {
            this.keystorePath = keystorePath;
            this.keystorePassword = keystorePassword;
            this.clientId = clientId;
            this.platformId = platformId;
            this.componentId = componentId;
            this.localAAMAddress = localAAMAddress;
            this.componentOwnerUsername = componentOwnerUsername;
            this.componentOwnerPassword = componentOwnerPassword;
        }

        public String getKeystorePath() {
            return keystorePath;
        }

        public void setKeystorePath(String keystorePath) {
            this.keystorePath = keystorePath;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public void setKeystorePassword(String keystorePassword) {
            this.keystorePassword = keystorePassword;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getPlatformId() {
            return platformId;
        }

        public void setPlatformId(String platformId) {
            this.platformId = platformId;
        }

        public String getComponentId() {
            return componentId;
        }

        public void setComponentId(String componentId) {
            this.componentId = componentId;
        }

        public String getLocalAAMAddress() {
            return localAAMAddress;
        }

        public void setLocalAAMAddress(String localAAMAddress) {
            this.localAAMAddress = localAAMAddress;
        }

        public String getComponentOwnerUsername() {
            return componentOwnerUsername;
        }

        public void setComponentOwnerUsername(String componentOwnerUsername) {
            this.componentOwnerUsername = componentOwnerUsername;
        }

        public String getComponentOwnerPassword() {
            return componentOwnerPassword;
        }

        public void setComponentOwnerPassword(String componentOwnerPassword) {
            this.componentOwnerPassword = componentOwnerPassword;
        }
    }

    public static <T> T createClient(String baseUrl, Class<T> clientClass, SecurityConfiguration securityConfiguration)
            throws SecurityHandlerException {
        Feign.Builder builder = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder());

        if (securityConfiguration != null) {
            IComponentSecurityHandler secHandler = ComponentSecurityHandlerFactory
                    .getComponentSecurityHandler(securityConfiguration.getKeystorePath(),
                            securityConfiguration.getKeystorePassword(),
                            securityConfiguration.getClientId()+"@"+securityConfiguration.getPlatformId(),
                            securityConfiguration.getLocalAAMAddress(),
                            securityConfiguration.getComponentOwnerUsername(),
                            securityConfiguration.getComponentOwnerPassword()
                    );

            Client client = new SymbioteAuthorizationClient(
                    secHandler,
                    securityConfiguration.getComponentId() , securityConfiguration.getLocalAAMAddress(),
                    new Client.Default(null, null));

            logger.debug("Will use " + baseUrl + " to access to interworking interface");
            builder = builder.client(client);
        }

        return builder.target(clientClass, baseUrl);
    }

}
