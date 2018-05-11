package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class describing Platform Proxy task. Contains id of the task, list of resources task is attempting to connect to,
 * name of the Enabler Logic that requested the task and task recurrence interval.
 */
public class PlatformProxyTaskInfo {


    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("resources")
    private List<PlatformProxyResourceInfo> resources;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    // Subject to change to more human friendly format
    @JsonProperty("queryInterval_ms")
    private Long queryInterval_ms;


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

    public Long getQueryInterval_ms() {
        return queryInterval_ms;
    }
    public void setQueryInterval_ms(Long queryInterval_ms) {
        this.queryInterval_ms = queryInterval_ms;
    }

    public String getEnablerLogicName() { return enablerLogicName; }
    public void setEnablerLogicName(String enablerLogicName) {this.enablerLogicName = enablerLogicName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformProxyTaskInfo that = (PlatformProxyTaskInfo) o;

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (resources != null ? !resources.equals(that.resources) : that.resources != null) return false;
        if (enablerLogicName != null ? !enablerLogicName.equals(that.enablerLogicName) : that.enablerLogicName != null)
            return false;
        return queryInterval_ms != null ? queryInterval_ms.equals(that.queryInterval_ms) : that.queryInterval_ms == null;

    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (resources != null ? resources.hashCode() : 0);
        result = 31 * result + (enablerLogicName != null ? enablerLogicName.hashCode() : 0);
        result = 31 * result + (queryInterval_ms != null ? queryInterval_ms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlatformProxyTaskInfo{" +
                "taskId='" + taskId + '\'' +
                ", resources=" + resources +
                ", enablerLogicName='" + enablerLogicName + '\'' +
                ", queryInterval_ms=" + queryInterval_ms +
                '}';
    }
}
