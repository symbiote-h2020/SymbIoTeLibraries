package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.core.model.resources.*;
import java.util.List;

/**
 * Represents CIM-defined InputParameter parameter class
 *
 * Created by Mael on 28/03/2017.
 */
public class InputParameter extends Parameter {

    private final String name;
    private boolean mandatory;
    private List<Restriction> hasRestriction;
    private ParameterValue paramValue;

    
    public InputParameter(String name) {
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

    public List<Restriction> getHasRestriction() {
        return hasRestriction;
    }

    public void setHasRestriction(List<Restriction> hasRestriction) {
        this.hasRestriction = hasRestriction;
    }
    
    public void setParamValue(ParameterValue paramValue) {
        this.paramValue = paramValue;
    }

    public ParameterValue getParamValue() {
        return paramValue;
    }
}
