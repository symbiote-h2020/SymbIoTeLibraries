package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined ComplexDatatype
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class ComplexDatatype extends Datatype {

    @JsonProperty("dataProperties")
    private List<DataProperty> dataProperties;

    @JsonProperty("basedOnClass")
    private String basedOnClass;

    public List<DataProperty> getDataProperties() {
        return dataProperties;
    }

    public void setDataProperties(List<DataProperty> dataProperties) {
        this.dataProperties = dataProperties;
    }

    public String getBasedOnClass() {
        return basedOnClass;
    }

    public void setBasedOnClass(String basedOnClass) {
        this.basedOnClass = basedOnClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComplexDatatype that = (ComplexDatatype) o;

        if (dataProperties != null ? !dataProperties.equals(that.dataProperties) : that.dataProperties != null)
            return false;
        return basedOnClass != null ? basedOnClass.equals(that.basedOnClass) : that.basedOnClass == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataProperties != null ? dataProperties.hashCode() : 0);
        result = 31 * result + (basedOnClass != null ? basedOnClass.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComplexDatatype{" +
                "dataProperties=" + dataProperties +
                ", basedOnClass='" + basedOnClass + '\'' +
                "} " + super.toString();
    }
}
