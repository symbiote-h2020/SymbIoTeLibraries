package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * Represents CIM-defined Actuator class.
 *
 * Created by Mael on 28/03/2017.
 */
public class Actuator extends Resource {

    private String locatedAt;
    private List<ActuatingService> hasCapability;

    public Actuator() {
    }

    public String getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<ActuatingService> getHasCapability() {
        return hasCapability;
    }

    public void setHasCapability(List<ActuatingService> hasCapability) {
        this.hasCapability = hasCapability;
    }
}
