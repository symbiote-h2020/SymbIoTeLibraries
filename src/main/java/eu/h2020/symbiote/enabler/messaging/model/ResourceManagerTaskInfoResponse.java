package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;

import java.util.List;


public class ResourceManagerTaskInfoResponse extends ResourceManagerTaskInfoRequest{

    @JsonProperty("resourceIds")
    private List<String> resourceIds;

    public ResourceManagerTaskInfoResponse() {
    }

    public ResourceManagerTaskInfoResponse(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        setTaskId(resourceManagerTaskInfoRequest.getTaskId());
        setMinNoResources(resourceManagerTaskInfoRequest.getMinNoResources());
        setCoreQueryRequest(CoreQueryRequest.newInstance(resourceManagerTaskInfoRequest.getCoreQueryRequest()));
        setQueryInterval_ms(resourceManagerTaskInfoRequest.getQueryInterval_ms());
        setAllowCaching(resourceManagerTaskInfoRequest.getAllowCaching());
        setCachingInterval_ms(resourceManagerTaskInfoRequest.getCachingInterval_ms());
        setInformPlatformProxy(resourceManagerTaskInfoRequest.getInformPlatformProxy());
        setEnablerLogicName(resourceManagerTaskInfoRequest.getEnablerLogicName());
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }
    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

}
