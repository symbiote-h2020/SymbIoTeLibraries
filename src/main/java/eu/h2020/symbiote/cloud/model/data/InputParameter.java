package eu.h2020.symbiote.cloud.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.model.cim.Parameter;
import eu.h2020.symbiote.model.cim.Restriction;

import java.util.List;

/**
 * Represents CIM-defined InputParameter parameter class
 *
 * Created by Mael on 28/03/2017.
 */
public class InputParameter extends Parameter {

    @JsonProperty("name")
    private final String name;
    @JsonIgnore
    private boolean mandatory;
    @JsonIgnore
    private List<Restriction> restrictions;
    @JsonProperty("value")
    private String value;


    public InputParameter(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}