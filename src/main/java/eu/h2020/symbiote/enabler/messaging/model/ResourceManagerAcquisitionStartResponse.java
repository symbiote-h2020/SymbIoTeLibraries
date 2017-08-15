package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ResourceManagerAcquisitionStartResponse {

    @JsonProperty("resources")
    private List<ResourceManagerTaskInfoResponse> resources;

    public ResourceManagerAcquisitionStartResponse() {
    }

    public List<ResourceManagerTaskInfoResponse> getResources() {
        return resources;
    }
    public void setResources(List<ResourceManagerTaskInfoResponse> resources) {
        this.resources = resources;
    }

}
