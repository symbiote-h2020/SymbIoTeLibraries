package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Description of the service execution. {@link #resource} should be pointing to the registered Service resource. Provided
 * parameters will be send to this Service resource as input parameters.
 *
 * Created by Szymon Mueller on 26/02/2018.
 */
public class ServiceExecutionTaskInfo {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("resource")
    private PlatformProxyResourceInfo resource;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    @JsonProperty("parameters")
    private List<ServiceParameter> parameters;

    public ServiceExecutionTaskInfo(String taskId, PlatformProxyResourceInfo resource, String enablerLogicName, List<ServiceParameter> parameters) {
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
}

