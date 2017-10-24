package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CIM-defined class ComplexProperty.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class ComplexProperty extends DataProperty {

    @JsonProperty("datatype")
    private ComplexDatatype datatype;

    @JsonProperty("basedOnProperty")
    private String basedOnProperty;

    public ComplexDatatype getDatatype() {
        return datatype;
    }

    public void setDatatype(ComplexDatatype datatype) {
        this.datatype = datatype;
    }

    public String getBasedOnProperty() {
        return basedOnProperty;
    }

    public void setBasedOnProperty(String basedOnProperty) {
        this.basedOnProperty = basedOnProperty;
    }
}
