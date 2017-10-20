package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined InstanceOf Restriction class.
 *
 * Created by Szymon Mueller 26/07/2017.
 */
public class InstanceOfRestriction extends Restriction {

    @JsonProperty("instanceOfClass")
    private String instanceOfClass;

    @JsonProperty("valueProperty")
    private String valueProperty;

    public InstanceOfRestriction() {
    }

    public String getInstanceOfClass() {
        return instanceOfClass;
    }

    public void setInstanceOfClass(String instanceOfClass) {
        this.instanceOfClass = instanceOfClass;
    }

    public String getValueProperty() {
        return valueProperty;
    }

    public void setValueProperty(String valueProperty) {
        this.valueProperty = valueProperty;
    }
}
