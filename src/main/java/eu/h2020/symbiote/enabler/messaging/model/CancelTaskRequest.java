package eu.h2020.symbiote.enabler.messaging.model;

import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class CancelTaskRequest {
    private List<String> taskIdList;

    public CancelTaskRequest() {
        // empty constructor
    }

    public List<String> getTaskIdList() { return taskIdList; }
    public void setTaskIdList(List<String> taskIdList) { this.taskIdList = taskIdList; }
}
