package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class PlatformProxyAcquisitionStartRequest {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("resources")
    private List<PlatformProxyResourceInfo> resources;

    @JsonProperty("interval")
    private Integer interval;


    public PlatformProxyAcquisitionStartRequest() {
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

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
