package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest {

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    @JsonProperty("status")
    private ResourceManagerTaskInfoResponseStatus status;

    @JsonProperty("message")
    private String message;

    public ResourceManagerTaskInfoResponse() {
    }

    public ResourceManagerTaskInfoResponse(String taskId, Integer minNoResources, CoreQueryRequest coreQueryRequest,
                                           String queryInterval, Boolean allowCaching, String cachingInterval,
                                           Boolean informPlatformProxy, String enablerLogicName,
                                           SparqlQueryRequest sparqlQuery, List<String> resourceIds,
                                           ResourceManagerTaskInfoResponseStatus status, String message) {
        super(taskId, minNoResources, coreQueryRequest, queryInterval, allowCaching, cachingInterval,
                informPlatformProxy, enablerLogicName, sparqlQuery);
        setResourceIds(resourceIds);
        setStatus(status);
        setMessage(message);
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        super(resourceManagerTaskInfoRequest);
        setStatus(ResourceManagerTaskInfoResponseStatus.UNKNOWN);
        setResourceIds(new ArrayList<>());
        setMessage("");
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoResponse resourceManagerTaskInfoResponse) {
        this((ResourceManagerTaskInfoRequest) resourceManagerTaskInfoResponse);
        setStatus(resourceManagerTaskInfoResponse.getStatus());
        setResourceIds(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceIds()));
        setMessage(resourceManagerTaskInfoResponse.getMessage());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public ResourceManagerTaskInfoResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerTaskInfoResponseStatus status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;

        // null check
        if (o == null)
            return false;

        // type check and cast
        if (!(o instanceof ResourceManagerTaskInfoResponse))
            return false;

        ResourceManagerTaskInfoRequest request = (ResourceManagerTaskInfoRequest) o;
        ResourceManagerTaskInfoResponse response = (ResourceManagerTaskInfoResponse) o;

        // field comparison
        return super.equals(request)
                && Objects.equals(this.getResourceIds(), response.getResourceIds())
                && Objects.equals(this.getStatus(), response.getStatus())
                && Objects.equals(this.getMessage(), response.getMessage());
    }
}
