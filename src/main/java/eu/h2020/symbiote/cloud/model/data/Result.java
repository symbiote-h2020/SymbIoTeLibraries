package eu.h2020.symbiote.cloud.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents return value from using service resource.
 * 
 * @author <a href="mailto:mario.kusek@fer.hr">Mario Kušek</a>
 *
 * @param <D> concrete result of service
 */
public class Result<D> {

    @JsonIgnore
    private boolean isArray;

    @JsonIgnore
    private String datatype;

    @JsonProperty("value")
    private D value;

    
    public Result() {
    }
    
    public Result(boolean isArray, String datatype, D value) {
        this.isArray = isArray;
        this.datatype = datatype;
        this.value = value;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean isArray) {
        this.isArray = isArray;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public D getValue() {
        return value;
    }

    public void setValue(D value) {
        this.value = value;
    }
}
