package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Actuating Service class.
 *
 * Created by Mael on 28/03/2017.
 */
public class ActuatingService extends Service {

    @JsonProperty("actsOn")
    private FeatureOfInterest actsOn;
    @JsonProperty("affects")
    private List<String> affects; //Links to BIM properties - short name of the property is used

    public ActuatingService() {
    }

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
