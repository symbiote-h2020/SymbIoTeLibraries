package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest{

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    @JsonProperty("status")
    private ResourceManagerTaskInfoResponseStatus status;


    public ResourceManagerTaskInfoResponse() {
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        super(resourceManagerTaskInfoRequest);
        status = ResourceManagerTaskInfoResponseStatus.UNKNOWN;
        resourceIds = new ArrayList<>();
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoResponse resourceManagerTaskInfoResponse) {
        this((ResourceManagerTaskInfoRequest) resourceManagerTaskInfoResponse);
        setStatus(resourceManagerTaskInfoResponse.getStatus());
        setResourceIds(new ArrayList<>(resourceManagerTaskInfoResponse.getResourceIds()));
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public ResourceManagerTaskInfoResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerTaskInfoResponseStatus status) { this.status = status; }
}
