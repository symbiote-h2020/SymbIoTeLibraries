package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Property;

import java.util.List;

/**
 * CIM-defined Effect class.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class Effect {

    @JsonProperty("actsOn")
    private FeatureOfInterest actsOn;

    @JsonProperty("affects")
    private List<Property> affects;

    public FeatureOfInterest getActsOn() {
        return actsOn;
    }

    public void setActsOn(FeatureOfInterest actsOn) {
        this.actsOn = actsOn;
    }

    public List<Property> getAffects() {
        return affects;
    }

    public void setAffects(List<Property> affects) {
        this.affects = affects;
    }
}
