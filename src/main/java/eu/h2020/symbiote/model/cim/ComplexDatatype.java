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
}
