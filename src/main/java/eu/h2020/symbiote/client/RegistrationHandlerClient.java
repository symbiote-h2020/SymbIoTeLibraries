package eu.h2020.symbiote.client;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;
import eu.h2020.symbiote.cloud.model.internal.RdfCloudResourceList;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;
import java.util.Map;

@Headers("Accept: application/json")
public interface RegistrationHandlerClient {

    @RequestLine("GET " + ClientConstants.RH_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> getResources();

    @RequestLine("GET " + ClientConstants.RH_RESOURCE_PATH+"?resourceInternalId={internalId}")
    @Headers("Content-Type: application/json")
    CloudResource getResource(@Param("internalId") String resourceInternalId);

    @RequestLine("POST " + ClientConstants.RH_RESOURCE_PATH)
    @Headers("Content-Type: application/json")
    CloudResource addResource(CloudResource resource);

    @RequestLine("POST " + ClientConstants.RH_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> addResources(List<CloudResource> resources);

    @RequestLine("POST " + ClientConstants.RH_RDF_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> addRdfResources(RdfCloudResourceList resources);

    @RequestLine("PUT " + ClientConstants.RH_RESOURCE_PATH)
    @Headers("Content-Type: application/json")
    CloudResource updateResource(CloudResource resource);

    @RequestLine("PUT " + ClientConstants.RH_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> updateResources(List<CloudResource> resources);

    @RequestLine("PUT " + ClientConstants.RH_SYNC_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> sync();

    @RequestLine("DELETE " + ClientConstants.RH_RESOURCE_PATH+"?resourceInternalId={internalId}")
    @Headers("Content-Type: application/json")
    CloudResource deleteResource(@Param("internalId") String resourceInternalId);

    @RequestLine("DELETE " + ClientConstants.RH_RESOURCES_PATH+"?resourceInternalIds={internalIds}")
    @Headers("Content-Type: application/json")
    List<CloudResource> deleteResources(@Param("internalIds") List<String> resourceInternalId);

    @RequestLine("DELETE " + ClientConstants.RH_CLEAR_PATH)
    @Headers("Content-Type: application/json")
    void clearResources();

    @RequestLine("POST " + ClientConstants.RH_LOCAL_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> addLocalResources(List<CloudResource> input);

    @RequestLine("PUT " + ClientConstants.RH_LOCAL_RESOURCES_PATH)
    @Headers("Content-Type: application/json")
    List<CloudResource> updateLocalResources(List<CloudResource> input);

    @RequestLine("DELETE " + ClientConstants.RH_LOCAL_RESOURCES_PATH+"?resourceIds={resourceIds}")
    @Headers("Content-Type: application/json")
    List<String> removeLocalResources(@Param("resourceIds")List<String> resourceIds);

    @RequestLine("PUT " + ClientConstants.RH_LOCAL_RESOURCES_SHARE_PATH)
    @Headers("Content-Type: application/json")
    Map<String, List<CloudResource>> shareResources(Map<String, Map<String, Boolean>> input);

    @RequestLine("DELETE " + ClientConstants.RH_LOCAL_RESOURCES_SHARE_PATH)
    @Headers("Content-Type: application/json")
    Map<String, List<CloudResource>> unshareResources(Map<String, List<String>> input);

    @RequestLine("PUT " + ClientConstants.RH_UPDATE_INTERWORKING_API)
    @Headers("Content-Type: text/plain")
    List<CloudResource> updateInterworkingURL(String url);

}
