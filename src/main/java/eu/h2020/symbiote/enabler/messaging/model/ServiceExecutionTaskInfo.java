package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;

/**
 * Description of the service execution. {@link #resource} should be pointing to the registered Service resource. Provided
 * parameters will be send to this Service resource as input parameters.
 *
 * Created by Szymon Mueller on 26/02/2018.
 */
public class ServiceExecutionTaskInfo {

    private String taskId;

    private PlatformProxyResourceInfo resource;

    private String enablerLogicName;

    private List<ServiceParameter> parameters;

    @JsonCreator
    @PersistenceConstructor
    public ServiceExecutionTaskInfo( @JsonProperty("taskId") String taskId,
                                     @JsonProperty("resource") PlatformProxyResourceInfo resource,
                                     @JsonProperty("enablerLogicName") String enablerLogicName,
                                     @JsonProperty("parameters") List<ServiceParameter> parameters) {
        this.taskId = taskId;
        this.resource = resource;
        this.enablerLogicName = enablerLogicName;
        this.parameters = parameters;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public PlatformProxyResourceInfo getResource() {
        return resource;
    }

    public void setResource(PlatformProxyResourceInfo resource) {
        this.resource = resource;
    }

    public String getEnablerLogicName() {
        return enablerLogicName;
    }

    public void setEnablerLogicName(String enablerLogicName) {
        this.enablerLogicName = enablerLogicName;
    }

    public List<ServiceParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ServiceParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceExecutionTaskInfo that = (ServiceExecutionTaskInfo) o;

        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        if (enablerLogicName != null ? !enablerLogicName.equals(that.enablerLogicName) : that.enablerLogicName != null)
            return false;
        return parameters != null ? parameters.equals(that.parameters) : that.parameters == null;

    }

    @Override
    public int hashCode() {
        int result = taskId != null ? taskId.hashCode() : 0;
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (enablerLogicName != null ? enablerLogicName.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceExecutionTaskInfo{" +
                "taskId='" + taskId + '\'' +
                ", resource=" + resource +
                ", enablerLogicName='" + enablerLogicName + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}

