package eu.h2020.symbiote.client;

import eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory;
import eu.h2020.symbiote.client.interfaces.*;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.IAAMClient;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Set;

/**
 * Factory for creating a concreate symbIoTe client factory based on the
 * provided configuration. For now there is just one concrete factory
 * implementation, but we followed the abstract factory pattern to facilitate
 * future extension
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
     * @throws SecurityHandlerException on creation error (e.g. problem with the
     * wallet)
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

    /**
     * Get a Search Client for querying Core Search
     *
     * @return a Search client
     */
    public abstract SearchClient getSearchClient();

    /**
     * Get a Core Resource Access Monitor (CRAM) Client for querying CRAM
     *
     * @return a CRAM client
     */
    public abstract CRAMClient getCramClient();

    /**
     * Get a Registration Handler (RH) client for querying the RH of a specific
     * platform
     *
     * @param platformId the platform which we want to communicate with
     * @return a RH client
     */
    public abstract RHClient getRHClient(String platformId);

    /**
     * Get a Resource Access Proxy (RAP) client for communicating with RAPs of
     * different platforms
     *
     * @return a RAP client
     */
    public abstract RAPClient getRapClient();

    /**
     * Get a Resource Access Proxy (RAP) client with support for semantics for
     * communicating with RAPs of different platforms
     *
     * @return a RAP client with support for semantics
     */
    public abstract SemanticRAPClient getSemanticRapClient();

    /**
     * Get a Platform Registry (PR) client for communicating with the PR of a
     * specific platform
     *
     * @param platformId the platform which we want to communicate with
     * @return a PR client
     */
    public abstract PRClient getPRClient(String platformId);

    /**
     * Get a Subscription Manager (SM) client for querying the SM of a specific
     * platform
     *
     * @param platformId the platform which we want to communicate with
     * @return a SM client
     */
    public abstract SMClient getSMClient(String platformId);

    /**
     * Get an Authentication and Authorization (AAM) client to communicate with
     * an AAM of a specific platform
     *
     * @param platformId the platform which we want to communicate with
     * @return a AAM client
     */
    public abstract IAAMClient getAAMClient(String platformId);

    /**
     * Getting required certificates for home platforms. If the certificates are
     * already present, they are reused
     *
     * @param credentials a set of home platform credentials
     * @throws SecurityHandlerException
     */
    public abstract void initializeInHomePlatforms(Set<HomePlatformCredentials> credentials) throws SecurityHandlerException;

    /**
     * The type of factory. For now there is just one type but we followed the
     * abstract factory pattern to facilitate future extension
     */
    public enum Type {
        FEIGN
    }

    public static class Config {

        private final String coreAddress;
        private final String keystorePath;
        private final String keystorePassword;
        private final Type type;

        /**
         *
         * @param coreAddress the base address of the symbIoTe core
         * @param keystorePath the keystore path
         * @param keystorePassword the keystore password
         * @param type the type of factory
         */
        public Config(String coreAddress,
                String keystorePath,
                String keystorePassword,
                Type type) {
            this.coreAddress = coreAddress;
            this.keystorePath = keystorePath;
            this.keystorePassword = keystorePassword;
            this.type = type;
        }

        public String getCoreAddress() {
            return coreAddress;
        }

        public String getKeystorePath() {
            return keystorePath;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public Type getType() {
            return type;
        }
    }

    /**
     * A class for supplying home platform credentials to the security handler
     */
    public static class HomePlatformCredentials {

        private final String platformId;
        private final String username;
        private final String password;
        private final String clientId;

        /**
         *
         * @param platformId the platform id
         * @param username the user name in the AAM
         * @param password the password in of the user in AAM
         * @param clientId the preferred client name
         */
        public HomePlatformCredentials(String platformId, String username, String password, String clientId) {
            this.platformId = platformId;
            this.username = username;
            this.password = password;
            this.clientId = clientId;
        }

        public String getPlatformId() {
            return platformId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getClientId() {
            return clientId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            HomePlatformCredentials that = (HomePlatformCredentials) o;
            return Objects.equals(platformId, that.platformId)
                    && Objects.equals(username, that.username)
                    && Objects.equals(password, that.password)
                    && Objects.equals(clientId, that.clientId);
        }

        @Override
        public int hashCode() {

            return Objects.hash(platformId, username, password, clientId);
        }
    }
}
