package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class PlatformProxyUpdateRequest {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("newResources")
    private List<PlatformProxyResourceInfo> newResources;

    public PlatformProxyUpdateRequest() {
        // empty constructor
    }

    public PlatformProxyUpdateRequest(String taskId, List<PlatformProxyResourceInfo> newResources){
        this.taskId = taskId;
        this.newResources = newResources;
    }

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<PlatformProxyResourceInfo> getNewResources() {
        return newResources;
    }
    public void setNewResources(List<PlatformProxyResourceInfo> newResources) {
        this.newResources = newResources;
    }
}
