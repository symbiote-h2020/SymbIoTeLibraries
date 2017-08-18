package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined InputDatatype parameter class
 *
 * Created by Mael on 28/03/2017.
 */
public class Parameter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mandatory")
    private boolean mandatory;
    @JsonProperty("restrictions")
    private List<Restriction> restrictions;
    @JsonProperty("datatype")
    private Datatype datatype;

    public Parameter() {}

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

    public Datatype getDatatype() {
        return datatype;
    }

    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }
}
