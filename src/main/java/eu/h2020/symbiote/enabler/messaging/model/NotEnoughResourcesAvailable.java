package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Created by vasgl on 7/18/2017.
 */
public class NotEnoughResourcesAvailable {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("minNoResources")
    private Integer minNoResources;

    @JsonProperty("noResourcesAcquired")
    private Integer noResourcesAcquired;

    public NotEnoughResourcesAvailable() {
        // empty constructor
    }

    public NotEnoughResourcesAvailable(String taskId, Integer minNoResources, Integer noResourcesAcquired) {
        this.taskId = taskId;
        this.minNoResources = minNoResources;
        this.noResourcesAcquired = noResourcesAcquired;
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getMinNoResources() { return minNoResources; }
    public void setMinNoResources(Integer minNoResources) { this.minNoResources = minNoResources; }

    public Integer getNoResourcesAcquired() { return noResourcesAcquired; }
    public void setNoResourcesAcquired(Integer noResourcesAcquired) { this.noResourcesAcquired = noResourcesAcquired; }
}
