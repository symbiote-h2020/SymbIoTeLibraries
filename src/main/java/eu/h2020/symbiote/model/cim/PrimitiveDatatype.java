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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PrimitiveDatatype that = (PrimitiveDatatype) o;

        return baseDatatype != null ? baseDatatype.equals(that.baseDatatype) : that.baseDatatype == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (baseDatatype != null ? baseDatatype.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PrimitiveDatatype{" +
                "baseDatatype='" + baseDatatype + '\'' +
                "} " + super.toString();
    }
}
