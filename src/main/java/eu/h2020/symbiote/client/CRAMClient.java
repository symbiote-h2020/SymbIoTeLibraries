package eu.h2020.symbiote.client;

import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;

import java.util.List;

/**
 * Interface for querying the Core Resource Access Manager component
 *
 * @author Vasilis Glykantzis
 */
public interface CRAMClient {

    /**
     * Queries and validates the Core Resource Access Manager component
     *
     * @param resourceId the id of the resource
     * @return the Core Resource Access Manager response
     */
    ResourceUrlsResponse getResourceUrl(String resourceId);

    /**
     * Queries and validates the Core Resource Access Manager component
     *
     * @param resourceIds a list containing the ids of the resources
     * @return the Core Resource Access Manager response
     */
    ResourceUrlsResponse getResourceUrl(List<String> resourceIds);

    /**
     * Queries the Core Resource Access Manager component component without validating it
     *
     * @param resourceId the id of the resource
     * @return the Core Resource Access Manager response
     */
    ResourceUrlsResponse getResourceUrlWithoutValidation(String resourceId);

    /**
     * Queries the Core Resource Access Manager component component without validating it
     *
     * @param resourceIds a list containing the ids of the resources
     * @return the Core Resource Access Manager response
     */
    ResourceUrlsResponse getResourceUrlWithoutValidation(List<String> resourceIds);
}
