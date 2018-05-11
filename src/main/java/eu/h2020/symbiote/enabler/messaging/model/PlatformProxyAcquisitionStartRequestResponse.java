package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


/**
 * Response to acquisition start request for Plaform Proxy. Returns human readable status of the acquisition request
 * and taskId of the started acquisition.
 *
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformProxyAcquisitionStartRequestResponse that = (PlatformProxyAcquisitionStartRequestResponse) o;

        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return taskId != null ? taskId.equals(that.taskId) : that.taskId == null;

    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlatformProxyAcquisitionStartRequestResponse{" +
                "status='" + status + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
