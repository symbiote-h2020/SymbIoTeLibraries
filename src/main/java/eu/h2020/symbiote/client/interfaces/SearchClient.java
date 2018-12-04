package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

import java.util.Set;

/**
 * Interface for querying the Core Search component
 *
 * @author Vasilis Glykantzis
 */
public interface SearchClient {

    /**
     * Queries the Core Search with home token
     *
     * @param request           the request sent to the Search
     * @param serverValidation  if true it will validate the Search Component
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  the search response
     */
    QueryResponse search(CoreQueryRequest request, boolean serverValidation, Set<String> homePlatformIds);
    
    /**
     * Queries the Core Search with home token
     * @param request           the sparql query to send to Core
     * @param serverValidation  if true it will validate the Search Component
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  the search response
     */
    SparqlQueryResponse search(SparqlQueryRequest request, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Queries the Core Search with guest token
     *
     * @param request           the request send to the Search
     * @param serverValidation  if true it will validate the Search Component
     * @return                  the search response
     */
    QueryResponse searchAsGuest(CoreQueryRequest request, boolean serverValidation);
    
    /**
     * Queries the Core Search with guest token
     *
     * @param request           the sparql query to send to Core
     * @param serverValidation  if true it will validate the Search Component
     * @return                  the search response
     */
    SparqlQueryResponse searchAsGuest(SparqlQueryRequest request, boolean serverValidation);
}
