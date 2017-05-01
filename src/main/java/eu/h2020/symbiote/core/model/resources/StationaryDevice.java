package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Location;

import java.util.List;

/**
 * Class to represent a resource which is both an Actuator and a Stationary Service.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
public class StationaryDevice extends Resource {

    @JsonProperty("locatedAt")
    private Location locatedAt;

    @JsonProperty("capabilites")
    private List<ActuatingService> capabilities;

    @JsonProperty("featureOfInterest")
    private FeatureOfInterest featureOfInterest;

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public StationaryDevice() {
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

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
