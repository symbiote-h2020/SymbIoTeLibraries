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


    private String capabilityName;

    public ActuatorExecutionTaskInfo(@JsonProperty("taskId") String taskId,
                                     @JsonProperty("resource") PlatformProxyResourceInfo resource,
                                     @JsonProperty("enablerLogicName") String enablerLogicName,
                                     @JsonProperty("capabilityName") String capabilityName,
                                     @JsonProperty("parameters") List<ServiceParameter> parameters) {
        super(taskId, resource, enablerLogicName, parameters);
        this.capabilityName = capabilityName;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ActuatorExecutionTaskInfo that = (ActuatorExecutionTaskInfo) o;

        return capabilityName != null ? capabilityName.equals(that.capabilityName) : that.capabilityName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (capabilityName != null ? capabilityName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActuatorExecutionTaskInfo{" +
                "capabilityName='" + capabilityName + '\'' +
                "} " + super.toString();
    }
}
