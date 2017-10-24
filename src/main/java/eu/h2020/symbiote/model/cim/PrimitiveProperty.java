package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a primitive property defined in a CIM.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class PrimitiveProperty extends DataProperty {

    @JsonProperty("primitiveDatatype")
    private PrimitiveDatatype primitiveDatatype;

    @JsonProperty("basedOnProperty")
    private String basedOnProperty;

    public PrimitiveDatatype getPrimitiveDatatype() {
        return primitiveDatatype;
    }

    public void setPrimitiveDatatype(PrimitiveDatatype primitiveDatatype) {
        this.primitiveDatatype = primitiveDatatype;
    }

    public String getBasedOnProperty() {
        return basedOnProperty;
    }

    public void setBasedOnProperty(String basedOnProperty) {
        this.basedOnProperty = basedOnProperty;
    }
}
