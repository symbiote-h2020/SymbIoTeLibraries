package eu.h2020.symbiote.enabler.messaging.model;

import java.util.ArrayList;

/**
 * Created by vasgl on 7/18/2017.
 */
public class CancelTaskRequest {
    private ArrayList<String> taskIdList;

    public CancelTaskRequest() {
        // empty constructor
    }

    public ArrayList<String> getTaskIdList() { return taskIdList; }
    public void setTaskIdList(ArrayList<String> taskIdList) { this.taskIdList = taskIdList; }
}
