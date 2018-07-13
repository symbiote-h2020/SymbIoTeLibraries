package eu.h2020.symbiote.client;

import eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory;
import eu.h2020.symbiote.client.interfaces.*;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

import java.security.NoSuchAlgorithmException;

/**
 * Factory for creating a concreate symbIoTe client factory based on the provided configuration. For now there is
 * just one concrete factory implementation, but we followed the abstract factory pattern to facilitate future extension
 *
 * @author Vasilis Glykantzis
 */
public abstract class AbstractSymbIoTeClientFactory {

    public static final String CORE_INTERFACE_PATH = "/coreInterface";
    public static final String CORE_AAM_SUBPATH = CORE_INTERFACE_PATH;

    /**
     *
     * @param config the configuration
     * @return a concrete symbIoTe client factory
     * @throws SecurityHandlerException on creation error (e.g. problem with the wallet)
     * @throws NoSuchAlgorithmException
     */
    public static AbstractSymbIoTeClientFactory getFactory(Config config)
            throws SecurityHandlerException, NoSuchAlgorithmException {

        switch (config.getType()) {
            case FEIGN:
                return new SymbIoTeFeignClientFactory(config);
            default:
                return new SymbIoTeFeignClientFactory(config);
        }
    }

    public abstract SearchClient getSearchClient();

    public abstract CRAMClient getCramClient();

    public abstract RHClient getRHClient();

    public abstract RAPClient getRapClient();

    public abstract PRClient getPRClient();

    /**
     * The type of factory. For now there is just one type but we followed the abstract factory pattern to facilitate
     * future extension
     */
    public enum Type {
        FEIGN
    }

    public static class Config {
        private final String coreAddress;
        private final String keystorePath;
        private final String keystorePassword;
        private final String homePlatformId;
        private final String username;
        private final String password;
        private final String clientId;
        private final Type type;

        /**
         *
         * @param coreAddress       the base address of the symbIoTe core
         * @param keystorePath      the keystore path
         * @param keystorePassword  the keystore password
         * @param homePlatformId    the home Platform Id
         * @param username          the username in the home platform
         * @param password          the password in the home platform
         * @param clientId          the client id
         * @param type              the type of factory
         */
        public Config(String coreAddress,
                      String keystorePath,
                      String keystorePassword,
                      String homePlatformId,
                      String username,
                      String password,
                      String clientId,
                      Type type) {
            this.coreAddress = coreAddress;
            this.keystorePath = keystorePath;
            this.keystorePassword = keystorePassword;
            this.homePlatformId = homePlatformId;
            this.username = username;
            this.password = password;
            this.clientId = clientId;
            this.type = type;
        }

        public String getCoreAddress() { return coreAddress; }
        public String getKeystorePath() { return keystorePath; }
        public String getKeystorePassword() { return keystorePassword; }
        public String getHomePlatformId() { return homePlatformId; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getClientId() { return clientId; }
        public Type getType() { return type; }
    }
}
