package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Service class.
 *
 * Created by Szymon Mueller on 28/03/2017.
 */
public class Service extends Resource {

    @JsonProperty("name")
    private String name;
    @JsonProperty("outputDatatype")
    private Datatype outputDatatype;
    @JsonProperty("inputParameter")
    private List<InputDatatype> inputParameter;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Datatype getOutputDatatype() {
        return outputDatatype;
    }

    public void setOutputDatatype(Datatype outputDatatype) {
        this.outputDatatype = outputDatatype;
    }

    public List<InputDatatype> getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(List<InputDatatype> inputParameter) {
        this.inputParameter = inputParameter;
    }
}
