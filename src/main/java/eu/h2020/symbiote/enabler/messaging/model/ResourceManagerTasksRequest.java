package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ResourceManagerTasksRequest {

    @JsonProperty("tasks")
    private List<ResourceManagerTaskInfoRequest> tasks;


    public ResourceManagerTasksRequest() {
    }

    public ResourceManagerTasksRequest(List<ResourceManagerTaskInfoRequest> tasks) {
        setTasks(tasks);
    }

    public List<ResourceManagerTaskInfoRequest> getTasks() { return tasks; }
    public void setTasks(List<ResourceManagerTaskInfoRequest> tasks) { this.tasks = tasks; }

    
	@Override
	public String toString() {
		return "ResourceManagerTasksRequest [tasks=" + tasks + "]";
	}
}
