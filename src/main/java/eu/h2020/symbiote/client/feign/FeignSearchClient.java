package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.commons.SecurityConstants;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.CORE_INTERFACE_PATH;

/**
 * symbIoTe search client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignSearchClient implements SearchClient {

    private static final Log logger = LogFactory.getLog(FeignSearchClient.class);
    private static final String SEARCH_BASE_URL = CORE_INTERFACE_PATH + "/query";

    private final SearchI searchClient;
    private final SearchI searchClientWithoutValidation;
    private final SearchI searchClientAsGuest;
    private final SearchI searchClientAsGuestWithoutValidation;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param coreAddress       the base address of the symbIoTe core
     * @param homePlatformId    the home Platform Id
     * @param username          the username in the home platform
     * @param password          the password in the home platform
     * @param clientId          the client id
     */
    public FeignSearchClient(ISecurityHandler securityHandler,
                             String coreAddress,
                             String homePlatformId,
                             String username,
                             String password,
                             String clientId) {

        this.searchClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        homePlatformId,
                        username,
                        password,
                        clientId,
                        ComponentIdentifiers.CORE_SEARCH,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        true))
                .target(SearchI.class, coreAddress);

        this.searchClientWithoutValidation = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        homePlatformId,
                        username,
                        password,
                        clientId,
                        ComponentIdentifiers.CORE_SEARCH,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        false))
                .target(SearchI.class, coreAddress);

        this.searchClientAsGuest = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.CORE_SEARCH,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        true))
                .target(SearchI.class, coreAddress);

        this.searchClientAsGuestWithoutValidation = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.CORE_SEARCH,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        false))
                .target(SearchI.class, coreAddress);

    }

    @Override
    public QueryResponse search(CoreQueryRequest request, boolean serverValidation) {
        return serverValidation ?
                searchClient.query(request.buildRequestParametersMap()) :
                searchClientWithoutValidation.query(request.buildRequestParametersMap());
    }

    @Override
    public QueryResponse searchAsGuest(CoreQueryRequest request, boolean serverValidation) {
        return serverValidation ?
                searchClientWithoutValidation.query(request.buildRequestParametersMap()) :
                searchClientAsGuestWithoutValidation.query(request.buildRequestParametersMap());
    }

    private interface SearchI {

        @RequestLine("GET " + SEARCH_BASE_URL)
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        QueryResponse query(@QueryMap Map<String, String> queryMap);
    }
}
