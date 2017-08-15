package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ResourceManagerAcquisitionStartRequest {

    @JsonProperty("resources")
    private List<ResourceManagerTaskInfoRequest> resources;


    public ResourceManagerAcquisitionStartRequest() {
    }

    public List<ResourceManagerTaskInfoRequest> getResources() {
        return resources;
    }
    public void setResources(List<ResourceManagerTaskInfoRequest> resources) {
        this.resources = resources;
    }

}
