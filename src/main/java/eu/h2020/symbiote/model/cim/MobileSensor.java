package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Mobile Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
public class MobileSensor extends Device {

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public MobileSensor() {
    }

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
