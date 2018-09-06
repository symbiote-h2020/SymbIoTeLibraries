package eu.h2020.symbiote.client.ssp.feign;

import eu.h2020.symbiote.client.ssp.interfaces.InnkeeperClient;
import eu.h2020.symbiote.security.ComponentSecurityHandlerFactory;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.handler.IComponentSecurityHandler;

import java.util.Objects;

/**
 * Factory for creating a concrete symbIoTe client factory for SSP components based on the provided configuration
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeFeignSSPPlatformClientFactory {


    /**
     * Get an Innkeeper for interacting with the SSP Innkeeper component
     *
     * @return  an Innkeeper client
     */
    public static InnkeeperClient getInnkeeperClient(Config config) throws SecurityHandlerException {
        IComponentSecurityHandler securityHandler = ComponentSecurityHandlerFactory.getComponentSecurityHandler(
                config.keystorePath, config.keystorePassword,
                config.clientId.contains("@") ? config.clientId : config.clientId + '@' + config.sspId,
                config.localAAMAddress, config.componentOwnerUsername, config.componentOwnerPassword
        );
        return new FeignInnkeeperClient(securityHandler, config.sspId, config.sspAddress);
    }


    /**
     * Class for specifying the configuration
     */
    public static class Config {
        private final String keystorePath;
        private final String keystorePassword;
        private final String sspId;
        private final String localAAMAddress;
        private final String sspAddress;
        private final String clientId;
        private final String componentOwnerUsername;
        private final String componentOwnerPassword;

        /**
         *
         * @param keystorePath              the keystore path
         * @param keystorePassword          the keystore password
         * @param sspId                     the id of the target SSP
         * @param localAAMAddress           the address of the AAM of the SSP
         * @param sspAddress                the address of the symbIoTe SmartSpace
         * @param clientId                  the client id in the form of {clientId}@{sspId}
         * @param componentOwnerUsername    the component owner username as specified in AAM
         * @param componentOwnerPassword    the component owner password as specified in AAM
         */
        public Config(String keystorePath, String keystorePassword, String sspId, String localAAMAddress,
                      String sspAddress, String clientId, String componentOwnerUsername, String componentOwnerPassword) {
            this.keystorePath = keystorePath;
            this.keystorePassword = keystorePassword;
            this.sspId = sspId;
            this.localAAMAddress = localAAMAddress;
            this.sspAddress = sspAddress;
            this.clientId = clientId;
            this.componentOwnerUsername = componentOwnerUsername;
            this.componentOwnerPassword = componentOwnerPassword;
        }

        public String getKeystorePath() { return keystorePath; }
        public String getKeystorePassword() { return keystorePassword; }
        public String getSspId() { return sspId; }
        public String getLocalAAMAddress() { return localAAMAddress; }
        public String getSspAddress() { return sspAddress; }
        public String getClientId() { return clientId; }
        public String getComponentOwnerUsername() { return componentOwnerUsername; }
        public String getComponentOwnerPassword() { return componentOwnerPassword; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Config config = (Config) o;
            return Objects.equals(keystorePath, config.keystorePath) &&
                    Objects.equals(keystorePassword, config.keystorePassword) &&
                    Objects.equals(sspId, config.sspId) &&
                    Objects.equals(localAAMAddress, config.localAAMAddress) &&
                    Objects.equals(sspAddress, config.sspAddress) &&
                    Objects.equals(clientId, config.clientId) &&
                    Objects.equals(componentOwnerUsername, config.componentOwnerUsername) &&
                    Objects.equals(componentOwnerPassword, config.componentOwnerPassword);
        }

        @Override
        public int hashCode() {
            return Objects.hash(keystorePath, keystorePassword, sspId, localAAMAddress, sspAddress,
                    clientId, componentOwnerUsername, componentOwnerPassword);
        }

        @Override
        public String toString() {
            return "Config{" +
                    "keystorePath='" + keystorePath + '\'' +
                    ", keystorePassword='" + keystorePassword + '\'' +
                    ", sspId='" + sspId + '\'' +
                    ", localAAMAddress='" + localAAMAddress + '\'' +
                    ", sspAddress='" + sspAddress + '\'' +
                    ", clientId='" + clientId + '\'' +
                    ", componentOwnerUsername='" + componentOwnerUsername + '\'' +
                    ", componentOwnerPassword='" + componentOwnerPassword + '\'' +
                    '}';
        }
    }
}
