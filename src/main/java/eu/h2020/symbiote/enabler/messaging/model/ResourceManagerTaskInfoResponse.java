package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest{

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    public ResourceManagerTaskInfoResponse() {
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        setTaskId(resourceManagerTaskInfoRequest.getTaskId());
        setCount(resourceManagerTaskInfoRequest.getCount());
        setLocation(resourceManagerTaskInfoRequest.getLocation());
        setObservesProperty(resourceManagerTaskInfoRequest.getObservesProperty());
        setInterval(resourceManagerTaskInfoRequest.getInterval());
        setAllowCaching(resourceManagerTaskInfoRequest.getAllowCaching());
        setCachingInterval(resourceManagerTaskInfoRequest.getCachingInterval());
        setInformPlatformProxy(resourceManagerTaskInfoRequest.getInformPlatformProxy());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

}
