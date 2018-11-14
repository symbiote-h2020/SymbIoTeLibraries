package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.CORE_INTERFACE_PATH;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.HOME_PLATFORM_IDS_HEADER;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * symbIoTe search client based on Feign
 *
 * @author Vasilis Glykantzis, Michael Jacoby
 */
public class FeignSearchClient implements SearchClient {

    private static final Log logger = LogFactory.getLog(FeignSearchClient.class);
    private static final String SEARCH_BASE_URL = CORE_INTERFACE_PATH + "/query";
    private static final String SEARCH_BASE_URL_SPARQL = CORE_INTERFACE_PATH + "/sparqlQuery";

    private final SearchI searchClient;

    /**
     *
     * @param securityHandler the security handler implementation that is going
     * to be used
     * @param coreAddress the base address of the symbIoTe core
     */
    public FeignSearchClient(ISecurityHandler securityHandler, String coreAddress) {

        this.searchClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.CORE_SEARCH,
                        SecurityConstants.CORE_AAM_INSTANCE_ID))
                .target(SearchI.class, coreAddress);

    }

    @Override
    public QueryResponse search(CoreQueryRequest request, boolean serverValidation, Set<String> homePlatformIds) {
        return searchClient.query(request.buildRequestParametersMap(), serverValidation, homePlatformIds);
    }

    @Override
    public SparqlQueryResponse search(SparqlQueryRequest request, boolean serverValidation, Set<String> homePlatformIds) {
        return searchClient.querySparql(request, serverValidation, homePlatformIds);
    }

    @Override
    public QueryResponse searchAsGuest(CoreQueryRequest request, boolean serverValidation) {
        return searchClient.query(request.buildRequestParametersMap(), serverValidation, new HashSet<>());
    }

    @Override
    public SparqlQueryResponse searchAsGuest(SparqlQueryRequest request, boolean serverValidation) {
        return searchClient.querySparql(request, serverValidation, new HashSet<>());
    }

    interface SearchI {

        @RequestLine("GET " + SEARCH_BASE_URL)
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        QueryResponse query(@QueryMap Map<String, String> queryMap,
                @Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);

        @RequestLine("POST " + SEARCH_BASE_URL_SPARQL)
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        SparqlQueryResponse querySparql(SparqlQueryRequest request,
                @Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);
    }
}
