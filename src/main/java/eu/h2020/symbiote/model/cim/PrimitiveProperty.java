package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a primitive property defined in a CIM.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class PrimitiveProperty extends DataProperty {

    @JsonProperty("rdfsDatatype")
    private RdfsDatatype rdfsDatatype;

    @JsonProperty("basedOnProperty")
    private String basedOnProperty;

    public RdfsDatatype getRdfsDatatype() {
        return rdfsDatatype;
    }

    public void setRdfsDatatype(RdfsDatatype rdfsDatatype) {
        this.rdfsDatatype = rdfsDatatype;
    }

    public String getBasedOnProperty() {
        return basedOnProperty;
    }

    public void setBasedOnProperty(String basedOnProperty) {
        this.basedOnProperty = basedOnProperty;
    }
}
