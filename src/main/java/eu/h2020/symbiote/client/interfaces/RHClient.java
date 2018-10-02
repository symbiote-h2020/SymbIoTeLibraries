package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;
import eu.h2020.symbiote.cloud.model.internal.RdfCloudResourceList;

import java.util.List;
import java.util.Map;

/**
 * Interface for querying the Platform Registration Handler component
 *
 * @author Vasilis Glykantzis
 */
public interface RHClient {

    /**
     * Get all registered resources (both L1 and L2)
     *
     * @return a list of {@link CloudResource} describing the registered L1 and L2 resources
     */
    List<CloudResource> getResources();

    /**
     * Get specific registered resource
     *
     * @param resourceInternalId the internal id of the resource
     * @return a {@link CloudResource} describing the registered resource metadata
     */
    CloudResource getResource(String resourceInternalId);

    /**
     * Register L1 resource
     *
     * @param resource a {@link CloudResource} describing the L1 resource metadata
     * @return a {@link CloudResource} describing the registered resource metadata
     */
    CloudResource addL1Resource(CloudResource resource);

    /**
     * Register a list of L1 resources
     *
     * @param resources a list of {@link CloudResource} describing the L1 resource metadata
     * @return a list of {@link CloudResource} describing the registered resources
     */
    List<CloudResource> addL1Resources(List<CloudResource> resources);

    /**
     * Register a list of L1 resources using RDF
     *
     * @param resources a list of {@link RdfCloudResourceList} describing the L1 resource metadata
     * @return a list of {@link CloudResource} describing the registered resource metadata
     */
    List<CloudResource> addL1RdfResources(RdfCloudResourceList resources);

    /**
     * Update the metadata of a L1 resource
     *
     * @param resource a {@link CloudResource} describing the resource metadata
     * @return a {@link CloudResource} describing the updated resource metadata
     */
    CloudResource updateL1Resource(CloudResource resource);

    /**
     * Update the metadata of a list of L1 resources
     *
     * @param resources a list of {@link CloudResource} describing the L1 resource metadata
     * @return a list of {@link CloudResource} describing the updated resource metadata
     */
    List<CloudResource> updateL1Resources(List<CloudResource> resources);

    /**
     * Sync the local L1 resources with Core Registry
     *
     * @return
     */
    List<CloudResource> sync();

    /**
     * Delete a L1 resource based on the internal id
     *
     * @param resourceInternalId the internal id of the resource
     * @return a {@link CloudResource} describing the deleted resource
     */
    CloudResource deleteL1Resource(String resourceInternalId);

    /**
     * Delete a list of L1 resources based on the internal ids
     *
     * @param resourceInternalIds the internal ids of the resources
     * @return a list of {@link CloudResource} describing the deleted resource metadata
     */
    List<CloudResource> deleteL1Resources(List<String> resourceInternalIds);

    /**
     * Clear both L1 and L2 resources
     */
    void clearResources();

    /**
     * Register a list of L2 resources
     *
     * @param cloudResources a list of {@link CloudResource} describing the L2 resources
     * @return a list of {@link CloudResource} describing the registered L2 resource metadata
     */
    List<CloudResource> addL2Resources(List<CloudResource> cloudResources);

    /**
     * Update a list of L2 resources
     *
     * @param cloudResources a list of {@link CloudResource} describing the L2 resources
     * @return a list of {@link CloudResource} describing the updated L2 resource metadata
     */
    List<CloudResource> updateL2Resources(List<CloudResource> cloudResources);

    /**
     * Delete a list of L2 resources based on the internal ids
     *
     * @param resourceInternalIds the internal ids of the resources
     * @return a list of strings containing the internal ids of the resources
     */
    List<String> removeL2Resources(List<String> resourceInternalIds);

    /**
     * Share already registered L2 metadata to federations
     *
     * @param input a map which has as keys the federation ids and as values another map, which has as keys
     *              the internal ids of the resources and as values a flag showing if the resource is bartered or not
     * @return a map which has as keys has the federations ids and as values a list of the resource metadata
     *              which have been shared to that federation.
     */
    Map<String, List<CloudResource>> shareL2Resources(Map<String, Map<String, Boolean>> input);

    /**
     * Unshare already registered L2 metadata from federations
     * @param input a map which has as keys the federation ids and as values a list of the resource internal ids
     *              where the resource is going to be unshared from
     * @return a map which has as keys the federation ids and as values a list of resource metadata
     *              that were removed from that federation.
     */
    Map<String, List<CloudResource>> unshareL2Resources(Map<String, List<String>> input);

}
