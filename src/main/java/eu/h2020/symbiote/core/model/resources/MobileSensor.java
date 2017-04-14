package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Property;

import java.util.List;

/**
 * Represents CIM-defined Mobile Sensor class.
 *
 * Created by Mael on 28/03/2017.
 */
public class MobileSensor extends Resource {

    @JsonProperty("locatedAt")
    private String locatedAt;
    @JsonProperty("observesProperty")
    private List<Property> observesProperty;

    public MobileSensor() {
    }

    public String getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(String locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<Property> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<Property> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
