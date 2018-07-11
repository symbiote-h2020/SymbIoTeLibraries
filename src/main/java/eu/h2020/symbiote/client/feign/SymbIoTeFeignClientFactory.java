package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.SearchClient;
import eu.h2020.symbiote.security.handler.ISecurityHandler;

/**
 * Factory for creating Feign symbIoTe clients
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeFeignClientFactory extends AbstractSymbIoTeClientFactory {

    private final ISecurityHandler securityHandler;
    private final String coreAddress;
    private final String homePlatformId;
    private final String username;
    private final String password;
    private final String clientId;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param coreAddress       the base address of the symbIoTe core
     * @param homePlatformId    the home Platform Id
     * @param username          the username in the home platform
     * @param password          the password in the home platform
     * @param clientId          the client id
     */
    public SymbIoTeFeignClientFactory(ISecurityHandler securityHandler,
                                      String coreAddress,
                                      String homePlatformId,
                                      String username,
                                      String password,
                                      String clientId) {
        this.securityHandler = securityHandler;
        this.coreAddress = coreAddress;
        this.homePlatformId = homePlatformId;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
    }

    @Override
    public SearchClient getSearchClient() {
        return new FeignSearchClient(securityHandler, coreAddress, homePlatformId, username, password, clientId);
    }
}
