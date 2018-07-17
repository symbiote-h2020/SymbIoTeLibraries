package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.cloud.model.internal.FederationSearchResult;
import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;

import java.util.Set;

/**
 * Interface for querying the Platform Registry component
 *
 * @author Vasilis Glykantzis
 */
public interface PRClient {

    /**
     * Queries the Platform Registry component with home token
     *
     * @param query             the request send to the Platform Registry
     * @param serverValidation  if true it will validate the Platform Registry Component
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  the search result
     */
    FederationSearchResult search(PlatformRegistryQuery query, boolean serverValidation, Set<String> homePlatformIds);


    /**
     * Queries the Platform Registry component with guest token
     *
     * @param query             the request send to the Platform Registry
     * @param serverValidation  if true it will validate the Platform Registry Component
     * @return                  the search result
     */
    FederationSearchResult searchAsGuest(PlatformRegistryQuery query, boolean serverValidation);
}
