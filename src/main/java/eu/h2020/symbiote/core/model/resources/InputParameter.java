package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * Represents CIM-defined InputParameter parameter class
 *
 * Created by Mael on 28/03/2017.
 */
public class InputParameter extends Parameter {

    private String name;
    private boolean mandatory;
    private List<Restriction> hasRestriction;

    public InputParameter() {
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

    public List<Restriction> getHasRestriction() {
        return hasRestriction;
    }

    public void setHasRestriction(List<Restriction> hasRestriction) {
        this.hasRestriction = hasRestriction;
    }
}
