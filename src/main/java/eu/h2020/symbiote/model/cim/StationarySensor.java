package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Represents CIM-defined Stationary Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
public class StationarySensor extends Sensor {

    @JsonProperty("featureOfInterest")
    private FeatureOfInterest featureOfInterest;

    public StationarySensor() {
    }

    public FeatureOfInterest getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(FeatureOfInterest featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

}
