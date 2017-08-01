package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vasgl on 7/26/2017.
 */
public class ResourcesUpdated {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("newResources")
    private List<String> newResources;

    public ResourcesUpdated() {
        // empty constructor
    }

    public ResourcesUpdated(String taskId, List<String> newResources) {
        this.taskId = taskId;
        this.newResources = newResources;
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public List<String> getNewResources() { return  newResources; }
    public void setNewResources(List<String> newResources) { this.newResources = newResources; }
}