package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.ci.QueryResourceResult;

import java.util.List;

/**
 * Created by vasgl on 7/26/2017.
 */
public class ResourcesUpdated {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("newResources")
    private List<String> newResources;

    @JsonProperty("resourceDescriptions")
    private List<QueryResourceResult> resourceDescriptions;

    public ResourcesUpdated() {
        // empty constructor
    }

    public ResourcesUpdated(String taskId, List<String> newResources, List<QueryResourceResult> resourceDescriptions) {
        this.taskId = taskId;
        this.newResources = newResources;
        this.resourceDescriptions = resourceDescriptions;
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public List<String> getNewResources() { return  newResources; }
    public void setNewResources(List<String> newResources) { this.newResources = newResources; }

    public List<QueryResourceResult> getResourceDescriptions() {
        return resourceDescriptions;
    }

    public void setResourceDescriptions(List<QueryResourceResult> resourceDescriptions) {
        this.resourceDescriptions = resourceDescriptions;
    }
}