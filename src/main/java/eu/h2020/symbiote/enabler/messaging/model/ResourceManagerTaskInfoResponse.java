package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

import java.util.ArrayList;
import java.util.List;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest{

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    @JsonProperty("status")
    private ResourceManagerResponseStatus status;


    public ResourceManagerTaskInfoResponse() {
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        super(resourceManagerTaskInfoRequest);
        resourceIds = new ArrayList<>();
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoResponse resourceManagerTaskInfoResponse) {
        this((ResourceManagerTaskInfoRequest) resourceManagerTaskInfoResponse);
        resourceIds = new ArrayList<>(resourceManagerTaskInfoResponse.getResourceIds());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public ResourceManagerResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerResponseStatus status) { this.status = status; }
}
