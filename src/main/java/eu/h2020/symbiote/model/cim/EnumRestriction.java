package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Enum Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class EnumRestriction extends Restriction {

    @JsonProperty("values")
    private List<String> values;

    public EnumRestriction() {
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
