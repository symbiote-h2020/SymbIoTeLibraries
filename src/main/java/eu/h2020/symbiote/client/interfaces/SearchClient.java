package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

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
     * @return                  the search response
     */
    QueryResponse search(CoreQueryRequest request, boolean serverValidation);

    /**
     * Queries the Core Search with guest token
     *
     * @param request           the request send to the Search
     * @param serverValidation  if true it will validate the Search Component
     * @return                  the search response
     */
    QueryResponse searchAsGuest(CoreQueryRequest request, boolean serverValidation);
}
