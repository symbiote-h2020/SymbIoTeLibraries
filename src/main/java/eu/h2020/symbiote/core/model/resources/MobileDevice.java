package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Location;

import java.util.List;

/**
 * Class to represent a resource which is both an Actuator and a Mobile Service.
 *
 * Created by Mael on 28/03/2017.
 */
@Deprecated
public class MobileDevice extends Resource {

    @JsonProperty("locatedAt")
    private Location locatedAt;

    @JsonProperty("capabilites")
    private List<ActuatingService> capabilities;

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public MobileDevice() {
    }

    public Location getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(Location locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<ActuatingService> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<ActuatingService> capabilities) {
        this.capabilities = capabilities;
    }

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
