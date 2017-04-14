package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class PlatformProxyAcquisitionStartRequestResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("taskId")
    private String taskId;


    public PlatformProxyAcquisitionStartRequestResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
