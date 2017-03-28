package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * Represents CIM-defined Enum Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class EnumRestriction extends Restriction {

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
