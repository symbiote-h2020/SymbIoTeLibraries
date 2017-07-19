package eu.h2020.symbiote.enabler.messaging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class ProblematicResourcesInfo {
    private String taskId;
    private List<String> problematicResourceIds;

    public ProblematicResourcesInfo() {
        problematicResourceIds = new ArrayList<>();
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public List<String> getProblematicResourceIds() { return problematicResourceIds; }
    public void setProblematicResourceIds(List<String> problematicResourceIds) { this.problematicResourceIds = problematicResourceIds; }
}
