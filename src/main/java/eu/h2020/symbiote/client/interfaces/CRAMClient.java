package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;

import java.util.Set;

/**
 * Interface for querying the Core Resource Access Manager (CRAM) component
 *
 * @author Vasilis Glykantzis
 */
public interface CRAMClient {

    /**
     * Queries CRAM with home token for getting the url of a single resource
     *
     * @param resourceId        the id of the resource
     * @param serverValidation  if true it will validate CRAM
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrl(String resourceId, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Queries CRAM with home token for getting the urls of a list of resources
     *
     * @param resourceIds       a list containing the ids of the resources
     * @param serverValidation  if true it will validate CRAM
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrl(Set<String> resourceIds, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Queries CRAM with guest token for getting the url of a single resource
     *
     * @param resourceId        the id of the resource
     * @param serverValidation  if true it will validate CRAM
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrlAsGuest(String resourceId, boolean serverValidation);

    /**
     * Queries CRAM with guest token for getting the urls of a list of resources
     *
     * @param resourceIds       a list containing the ids of the resources
     * @param serverValidation  if true it will validate CRAM
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrlAsGuest(Set<String> resourceIds, boolean serverValidation);
}
