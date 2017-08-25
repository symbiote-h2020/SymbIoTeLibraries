package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ResourceManagerTasksResponse {

    @JsonProperty("resources")
    private List<ResourceManagerTaskInfoResponse> resources;

    @JsonProperty("status")
    private ResourceManagerAcquisitionStartResponseStatus status;

    public ResourceManagerTasksResponse() {
    }

    public List<ResourceManagerTaskInfoResponse> getResources() {
        return resources;
    }
    public void setResources(List<ResourceManagerTaskInfoResponse> resources) {
        this.resources = resources;
    }

    public ResourceManagerAcquisitionStartResponseStatus getStatus() { return status; }
    public void setStatus(ResourceManagerAcquisitionStartResponseStatus status) { this.status = status; }

}
