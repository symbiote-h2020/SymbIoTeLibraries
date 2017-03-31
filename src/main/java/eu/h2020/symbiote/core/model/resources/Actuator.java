package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Actuator class.
 *
 * Created by Mael on 28/03/2017.
 */
public class Actuator extends Resource {

    @JsonProperty("locatedAt")
    private String locatedAt;
    @JsonProperty("capabilites")
    private List<ActuatingService> capabilities;

    public Actuator() {
    }

    public String getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<ActuatingService> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<ActuatingService> capabilities) {
        this.capabilities = capabilities;
    }
}
