package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.cloud.model.internal.FederationSearchResult;
import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;

/**
 * Interface for querying the Platform Registry component
 *
 * @author Vasilis Glykantzis
 */
public interface PRClient {

    /**
     * Queries and validates the Platform Registry component
     *
     * @param query the request send to the Platform Registry
     * @return the search response
     */
    FederationSearchResult search(PlatformRegistryQuery query);


    /**
     * Queries the Platform Registry component without validating it
     *
     * @param query the request send to the Platform Registry
     * @return the search response
     */
    FederationSearchResult searchWithoutValidation(PlatformRegistryQuery query);
}
