package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;

import java.util.List;

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
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrl(String resourceId, boolean serverValidation);

    /**
     * Queries CRAM with home token for getting the urls of a list of resources
     *
     * @param resourceIds       a list containing the ids of the resources
     * @param serverValidation  if true it will validate CRAM
     * @return                  the CRAM response
     */
    ResourceUrlsResponse getResourceUrl(List<String> resourceIds, boolean serverValidation);

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
    ResourceUrlsResponse getResourceUrlAsGuest(List<String> resourceIds, boolean serverValidation);
}
