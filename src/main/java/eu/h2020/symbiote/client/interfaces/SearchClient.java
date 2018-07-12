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
     * Queries and validates the Core Search component
     *
     * @param request the request send to the Search
     * @return the search response
     */
    QueryResponse search(CoreQueryRequest request);

    /**
     * Queries the Core Search component without validating it
     *
     * @param request the request send to the Search
     * @return the search response
     */
    QueryResponse searchWithoutValidation(CoreQueryRequest request);
}
