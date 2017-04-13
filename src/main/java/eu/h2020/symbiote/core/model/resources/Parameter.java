package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined Parameter class (named Parameter in CIM v0.5).
 *
 * Created by Mael on 28/03/2017.
 */
public class Parameter {

    @JsonProperty("isArray")
    private boolean isArray;
    @JsonProperty("datatype")
    private String datatype;

    public Parameter() {
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
}
