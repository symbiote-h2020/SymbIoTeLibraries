package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class describing execution task for Actuator type of resources. It extends the service execution task
 * description with name of the Capability of the Actuator. {@link #resource} should point to the registered Actuator resource.
 *
 * Created by Szymon Mueller on 26/02/2018.
 */
public class ActuatorExecutionTaskInfo extends ServiceExecutionTaskInfo {

    @JsonProperty("capabilityName")
    private String capabilityName;

    public ActuatorExecutionTaskInfo(String taskId, PlatformProxyResourceInfo resource, String capabilityName, String enablerLogicName, List<ServiceParameter> parameters) {
        super(taskId, resource, enablerLogicName, parameters);
        this.capabilityName = capabilityName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
