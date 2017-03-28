package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * Represents CIM-defined Stationary Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
public class StationarySensor extends Resource {

    private String locatedAt;
    private String hasFeatureOfInterest;
    private List<String> observesProperty;

    public StationarySensor() {
    }

    public String getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }

    public String getHasFeatureOfInterest() {
        return hasFeatureOfInterest;
    }

    public void setHasFeatureOfInterest(String hasFeatureOfInterest) {
        this.hasFeatureOfInterest = hasFeatureOfInterest;
    }

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
