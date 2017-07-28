package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlatformProxyTaskInfo {


    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("resources")
    private List<PlatformProxyResourceInfo> resources;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    // Subject to change to more human friendly format
    @JsonProperty("queryInterval_ms")
    private Integer queryInterval_ms;


    public PlatformProxyTaskInfo() {
    }

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<PlatformProxyResourceInfo> getResources() {
        return resources;
    }
    public void setResources(List<PlatformProxyResourceInfo> resources) {
        this.resources = resources;
    }

    public Integer getQueryInterval_ms() {
        return queryInterval_ms;
    }
    public void setQueryInterval_ms(Integer queryInterval_ms) {
        this.queryInterval_ms = queryInterval_ms;
    }

    public String getEnablerLogicName() { return enablerLogicName; }
    public void setEnablerLogicName(String enablerLogicName) {this.enablerLogicName = enablerLogicName; }
}
