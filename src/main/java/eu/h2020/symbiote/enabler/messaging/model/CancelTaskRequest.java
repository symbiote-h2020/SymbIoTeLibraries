package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class CancelTaskRequest {

    @JsonProperty("taskIdList")
    private List<String> taskIdList;

    public CancelTaskRequest() {
        // empty constructor
        taskIdList = new ArrayList<>();
    }

    public List<String> getTaskIdList() { return taskIdList; }
    public void setTaskIdList(List<String> taskIdList) { this.taskIdList = taskIdList; }
}
