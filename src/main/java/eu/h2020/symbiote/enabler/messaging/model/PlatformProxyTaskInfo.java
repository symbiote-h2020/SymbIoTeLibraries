package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PlatformProxyTaskInfo {

    @JsonProperty("resourceId")
    private String resourceId;

    @JsonProperty("accessURL")
    private String accessURL;


    public PlatformProxyTaskInfo() {
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAccessURL() {
        return accessURL;
    }

    public void setAccessURL(String accessURL) {
        this.accessURL = accessURL;
    }
}
