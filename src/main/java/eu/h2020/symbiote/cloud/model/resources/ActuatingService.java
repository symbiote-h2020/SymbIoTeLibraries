package eu.h2020.symbiote.cloud.model.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.h2020.symbiote.cloud.model.data.observation.Property;
import java.util.List;

/**
 * Represents CIM-defined Actuating Service class.
 *
 * Created by Mael on 28/03/2017.
 */
public class ActuatingService extends Service {

    @JsonIgnore
    private String actsOn;
    @JsonIgnore
    private List<Property> affects;

    public ActuatingService() {
    }

    public String getActsOn() {
        return actsOn;
    }

    public void setActsOn(String actsOn) {
        this.actsOn = actsOn;
    }

    public List<Property> getAffects() {
        return affects;
    }

    public void setAffects(List<Property> affects) {
        this.affects = affects;
    }
}
