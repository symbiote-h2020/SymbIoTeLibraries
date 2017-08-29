package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ResourceManagerTasksResponse {

    @JsonProperty("tasks")
    private List<ResourceManagerTaskInfoResponse> tasks;

    @JsonProperty("status")
    private ResourceManagerTasksStatus status;

    @JsonProperty("message")
    private String message;

    public ResourceManagerTasksResponse() {
    }

    public ResourceManagerTasksResponse(List<ResourceManagerTaskInfoResponse> tasks,
                                        ResourceManagerTasksStatus status,
                                        String message) {
        setTasks(tasks);
        setStatus(status);
        setMessage(message);
    }

    public List<ResourceManagerTaskInfoResponse> getTasks() { return tasks; }
    public void setTasks(List<ResourceManagerTaskInfoResponse> tasks) { this.tasks = tasks; }

    public ResourceManagerTasksStatus getStatus() { return status; }
    public void setStatus(ResourceManagerTasksStatus status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
