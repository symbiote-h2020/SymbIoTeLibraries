package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ResourceManagerAcquisitionStartResponse {

    @JsonProperty("resources")
    private List<ResourceManagerTaskInfoResponse> resources;

    @JsonProperty("status")
    private ResourceManagerResponseStatus status;


    public ResourceManagerAcquisitionStartResponse() {
    }

    public List<ResourceManagerTaskInfoResponse> getResources() {
        return resources;
    }
    public void setResources(List<ResourceManagerTaskInfoResponse> resources) {
        this.resources = resources;
    }

    public ResourceManagerResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerResponseStatus status) { this.status = status; }

}
