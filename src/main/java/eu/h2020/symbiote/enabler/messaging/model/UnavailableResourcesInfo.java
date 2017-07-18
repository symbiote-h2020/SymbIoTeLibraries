package eu.h2020.symbiote.enabler.messaging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class UnavailableResourcesInfo {
    private String taskId;
    private List<String> unavailableResourceIds;

    public UnavailableResourcesInfo() {
        unavailableResourceIds = new ArrayList<>();
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public List<String> getUnavailableResourceId() { return unavailableResourceIds; }
    public void setUnavailableResourceId(List<String> unavailableResourceIds) { this.unavailableResourceIds = unavailableResourceIds; }
}
