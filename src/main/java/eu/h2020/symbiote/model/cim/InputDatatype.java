package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined InputDatatype parameter class
 *
 * Created by Mael on 28/03/2017.
 *
 * @deprecated Not in CIM 2.0
 */
@Deprecated
public class InputDatatype extends Datatype {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mandatory")
    private boolean mandatory;
    @JsonProperty("restrictions")
    private List<Restriction> restrictions;

    public InputDatatype() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
