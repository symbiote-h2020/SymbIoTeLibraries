package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private List<String> affects;

    public FeatureOfInterest getActsOn() {
        return actsOn;
    }

    public void setActsOn(FeatureOfInterest actsOn) {
        this.actsOn = actsOn;
    }

    public List<String> getAffects() {
        return affects;
    }

    public void setAffects(List<String> affects) {
        this.affects = affects;
    }
}
