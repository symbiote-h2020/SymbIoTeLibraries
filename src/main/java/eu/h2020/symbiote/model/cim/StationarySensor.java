package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Stationary Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
public class StationarySensor extends Resource {

    @JsonProperty("locatedAt")
    private Location locatedAt;
    @JsonProperty("featureOfInterest")
    private FeatureOfInterest featureOfInterest;
    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public StationarySensor() {
    }

    public Location getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(Location locatedAt) {
        this.locatedAt = locatedAt;
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
