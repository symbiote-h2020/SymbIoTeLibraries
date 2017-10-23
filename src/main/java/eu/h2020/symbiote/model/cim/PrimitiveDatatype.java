package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined rdfs:Datatype
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class PrimitiveDatatype extends Datatype{

    @JsonProperty("baseDatatype")
    private String baseDatatype;

    public String getBaseDatatype() {
        return baseDatatype;
    }

    public void setBaseDatatype(String baseDatatype) {
        this.baseDatatype = baseDatatype;
    }
}
