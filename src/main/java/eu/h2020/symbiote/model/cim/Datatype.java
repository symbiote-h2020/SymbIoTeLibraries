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
        @JsonSubTypes.Type(value = PrimitiveDatatype.class, name = "PrimitiveDatatype")

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Datatype datatype = (Datatype) o;

        return isArray == datatype.isArray;

    }

    @Override
    public int hashCode() {
        return (isArray ? 1 : 0);
    }

    @Override
    public String toString() {
        return "Datatype{" +
                "isArray=" + isArray +
                '}';
    }
}
