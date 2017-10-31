package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.ci.QueryResourceResult;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest {

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    @JsonProperty("resourceDescriptions")
    private List<QueryResourceResult> resourceDescriptions;

    @JsonProperty("status")
    private ResourceManagerTaskInfoResponseStatus status;

    @JsonProperty("message")
    private String message;


    public ResourceManagerTaskInfoResponse() {
        // empty constructor
    }


    /**
     *
     * @param taskId                the id of the requested task
     * @param minNoResources        the minimum number of required resources. The maximum number of resources is configured
     *                              by setting the maxNoResources, which defaults to ALL_AVAILABLE_RESOURCES
     * @param coreQueryRequest      the request which is propagated to the core
     * @param queryInterval         the query interval in ISO-8601 alternateExtended format that is propagated to the
     *                              Platform Proxy
     * @param allowCaching          if the results gotten from search are allowed to be cached for faster responses
     *                              in case of failing resources
     * @param cachingInterval       the caching interval of tasks resources in ISO-8601 alternateExtended format
     * @param informPlatformProxy   if Platform Proxy needs to be informed. If you want to receive back data set to true.
     *                              Otherwise, if you just need to query the Core for getting back the resource
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
    public ResourceManagerTaskInfoResponse(String taskId, Integer minNoResources,
                                           CoreQueryRequest coreQueryRequest, String queryInterval,
                                           Boolean allowCaching, String cachingInterval,
                                           Boolean informPlatformProxy, String enablerLogicName,
                                           SparqlQueryRequest sparqlQueryRequest, List<String> resourceIds,
                                           List<QueryResourceResult> resourceDescriptions,
                                           ResourceManagerTaskInfoResponseStatus status, String message) {
        super(taskId, minNoResources, coreQueryRequest, queryInterval, allowCaching, cachingInterval,
                informPlatformProxy, enablerLogicName, sparqlQueryRequest);
        setResourceIds(new ArrayList<>(resourceIds));
        setResourceDescriptions(new ArrayList<>(resourceDescriptions));
        setStatus(status);
        setMessage(message);
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        super(resourceManagerTaskInfoRequest);
        setStatus(ResourceManagerTaskInfoResponseStatus.UNKNOWN);
        setResourceIds(new ArrayList<>());
        setResourceDescriptions(new ArrayList<>());
        setMessage("");
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoResponse resourceManagerTaskInfoResponse) {
        this((ResourceManagerTaskInfoRequest) resourceManagerTaskInfoResponse);
        setStatus(resourceManagerTaskInfoResponse.getStatus());
        setResourceIds(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceIds()));
        setResourceDescriptions(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceDescriptions()));
        setMessage(resourceManagerTaskInfoResponse.getMessage());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

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

        if (getResourceIds() != null ? !getResourceIds().equals(that.getResourceIds()) : that.getResourceIds() != null)
            return false;
        if (getResourceDescriptions() != null ? !getResourceDescriptions().equals(that.getResourceDescriptions()) : that.getResourceDescriptions() != null)
            return false;
        if (getStatus() != that.getStatus()) return false;
        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getResourceIds() != null ? getResourceIds().hashCode() : 0;
        result = 31 * result + (getResourceDescriptions() != null ? getResourceDescriptions().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }
}
