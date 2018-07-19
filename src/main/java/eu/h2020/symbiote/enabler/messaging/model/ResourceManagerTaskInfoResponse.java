package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.ci.QueryResourceResult;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.*;
import java.util.stream.Collectors;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest {

    private List<String> resourceIds;

    private Map<String, String> resourceUrls;

    private List<QueryResourceResult> resourceDescriptions;

    private ResourceManagerTaskInfoResponseStatus status;

    private String message;


    /**
     * Defaulting maxResources to ALL_AVAILABLE
     *
     * @param taskId                the id of the requested task
     * @param minNoResources        the minimum number of required resources. The maximum number of resources is configured
     *                              by setting the maxNoResources, which defaults to ALL_AVAILABLE_RESOURCES
     * @param coreQueryRequest      the request which is propagated to the core
     * @param queryInterval         the getResourceUrls interval in ISO-8601 alternateExtended format that is propagated to the
     *                              Platform Proxy
     * @param allowCaching          if the results gotten from search are allowed to be cached for faster responses
     *                              in case of failing resources
     * @param cachingInterval       the caching interval of tasks resources in ISO-8601 alternateExtended format
     * @param informPlatformProxy   if Platform Proxy needs to be informed. If you want to receive back data set to true.
     *                              Otherwise, if you just need to getResourceUrls the Core for getting back the resource
     *                              descriptions, set to false
     * @param enablerLogicName      the enabler logic component which owns this task and it will receive updates for it
     * @param sparqlQueryRequest    the request in SPARQL. Set to null if you use CoreQueryRequest. If set overwrites
     *                              the CoreQueryRequest
     * @param resourceIds           the resourceIds of the acquired resources
     * @param resourceDescriptions  the resource descriptions
     * @param status                the status of the task
     * @param message               helpful message explaining status
     *
     * @throws IllegalArgumentException if queryInterval/cachingInterval has wrong format or both sparqlQueryRequest and coreQueryRequest are null
     * @see                         <a href="http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISOPeriodFormat.html#alternateExtended()">ISO-8601 alternateExtended format</a>
     */
    public ResourceManagerTaskInfoResponse(String taskId,
                                           Integer minNoResources,
                                           CoreQueryRequest coreQueryRequest,
                                           String queryInterval,
                                           Boolean allowCaching,
                                           String cachingInterval,
                                           Boolean informPlatformProxy,
                                           String enablerLogicName,
                                           SparqlQueryRequest sparqlQueryRequest,
                                           List<String> resourceIds,
                                           Map<String, String> resourceUrls,
                                           List<QueryResourceResult> resourceDescriptions,
                                           ResourceManagerTaskInfoResponseStatus status,
                                           String message) {
        super(taskId, minNoResources, coreQueryRequest, queryInterval, allowCaching, cachingInterval,
                informPlatformProxy, enablerLogicName, sparqlQueryRequest);
        setResourceIds(new ArrayList<>(resourceIds));
        setResourceUrls(new HashMap<>(resourceUrls));
        setResourceDescriptions(new ArrayList<>(resourceDescriptions));
        setStatus(status);
        setMessage(message);
    }

    /**
     *
     * @param taskId                the id of the requested task
     * @param minNoResources        the minimum number of required resources.
     * @param maxNoResources        the maximum number of resources
     * @param coreQueryRequest      the request which is propagated to the core
     * @param queryInterval         the getResourceUrls interval in ISO-8601 alternateExtended format that is propagated to the
     *                              Platform Proxy
     * @param allowCaching          if the results gotten from search are allowed to be cached for faster responses
     *                              in case of failing resources
     * @param cachingInterval       the caching interval of tasks resources in ISO-8601 alternateExtended format
     * @param informPlatformProxy   if Platform Proxy needs to be informed. If you want to receive back data set to true.
     *                              Otherwise, if you just need to getResourceUrls the Core for getting back the resource
     *                              descriptions, set to false
     * @param enablerLogicName      the enabler logic component which owns this task and it will receive updates for it
     * @param sparqlQueryRequest    the request in SPARQL. Set to null if you use CoreQueryRequest. If set overwrites
     *                              the CoreQueryRequest
     * @param resourceIds           the resourceIds of the acquired resources
     * @param resourceDescriptions  the resource descriptions
     * @param status                the status of the task
     * @param message               helpful message explaining status
     *
     * @throws IllegalArgumentException if queryInterval/cachingInterval has wrong format or both sparqlQueryRequest and coreQueryRequest are null
     * @see                         <a href="http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISOPeriodFormat.html#alternateExtended()">ISO-8601 alternateExtended format</a>
     */
    @PersistenceConstructor
    @JsonCreator
    public ResourceManagerTaskInfoResponse(@JsonProperty("taskId") String taskId,
                                           @JsonProperty("minNoResources") Integer minNoResources,
                                           @JsonProperty("maxNoResources") Integer maxNoResources,
                                           @JsonProperty("coreQueryRequest") CoreQueryRequest coreQueryRequest,
                                           @JsonProperty("queryInterval") String queryInterval,
                                           @JsonProperty("allowCaching") Boolean allowCaching,
                                           @JsonProperty("cachingInterval") String cachingInterval,
                                           @JsonProperty("informPlatformProxy") Boolean informPlatformProxy,
                                           @JsonProperty("enablerLogicName") String enablerLogicName,
                                           @JsonProperty("sparqlQueryRequest") SparqlQueryRequest sparqlQueryRequest,
                                           @JsonProperty("resourceIds") List<String> resourceIds,
                                           @JsonProperty("resourceUrls") Map<String, String> resourceUrls,
                                           @JsonProperty("resourceDescriptions") List<QueryResourceResult> resourceDescriptions,
                                           @JsonProperty("status") ResourceManagerTaskInfoResponseStatus status,
                                           @JsonProperty("message") String message) {
        super(taskId, minNoResources, maxNoResources, coreQueryRequest, queryInterval, allowCaching, cachingInterval,
                informPlatformProxy, enablerLogicName, sparqlQueryRequest);
        setResourceIds(new ArrayList<>(resourceIds));
        setResourceUrls(new HashMap<>(resourceUrls));
        setResourceDescriptions(new ArrayList<>(resourceDescriptions));
        setStatus(status);
        setMessage(message);
    }


    /**
     *
     * @param resourceManagerTaskInfoRequest the input request
     */
    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        super(resourceManagerTaskInfoRequest);
        setStatus(ResourceManagerTaskInfoResponseStatus.UNKNOWN);
        setResourceIds(new ArrayList<>());
        setResourceUrls(new HashMap<>());
        setResourceDescriptions(new ArrayList<>());
        setMessage("");
    }

    /**
     * Copy constructor
     *
     * @param resourceManagerTaskInfoResponse the input response
     */
    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoResponse resourceManagerTaskInfoResponse) {
        this((ResourceManagerTaskInfoRequest) resourceManagerTaskInfoResponse);
        setStatus(resourceManagerTaskInfoResponse.getStatus());
        setResourceIds(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceIds()));
        setResourceUrls(new HashMap<>(resourceManagerTaskInfoResponse.getResourceUrls()));
        setResourceDescriptions(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceDescriptions()));
        setMessage(resourceManagerTaskInfoResponse.getMessage());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Map<String, String> getResourceUrls() { return resourceUrls; }
    public void setResourceUrls(Map<String, String> resourceUrls) { this.resourceUrls = resourceUrls; }

    public List<QueryResourceResult> getResourceDescriptions() { return resourceDescriptions; }
    public void setResourceDescriptions(List<QueryResourceResult> resourceDescriptions) {
        this.resourceDescriptions = resourceDescriptions.stream().
                map(item -> new QueryResourceResult(item)).
                collect(Collectors.toList());
    }

    public ResourceManagerTaskInfoResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerTaskInfoResponseStatus status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceManagerTaskInfoResponse)) return false;
        if (!super.equals(o)) return false;
        ResourceManagerTaskInfoResponse that = (ResourceManagerTaskInfoResponse) o;
        return Objects.equals(resourceIds, that.resourceIds) &&
                Objects.equals(resourceUrls, that.resourceUrls) &&
                Objects.equals(resourceDescriptions, that.resourceDescriptions) &&
                status == that.status &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(resourceIds, resourceUrls, resourceDescriptions, status, message);
    }
}
