package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents CIM-defined Datatype class.
 *
 * Created by Mael on 28/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComplexDatatype.class, name = "ComplexDatatype"),
        @JsonSubTypes.Type(value = RdfsDatatype.class, name = "RdfsDatatype")

})
public class Datatype {

    @JsonProperty("isArray")
    private boolean isArray;

    public Datatype() {
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

}
