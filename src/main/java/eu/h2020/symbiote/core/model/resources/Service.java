package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined Service class.
 *
 * Created by Mael on 28/03/2017.
 */
public class Service extends Resource {

    @JsonProperty("name")
    private String name;
    @JsonProperty("outputParameter")
    private Parameter outputParameter;
    @JsonProperty("inputParameter")
    private List<InputParameter> inputParameter;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(Parameter outputParameter) {
        this.outputParameter = outputParameter;
    }

    public List<InputParameter> getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(List<InputParameter> inputParameter) {
        this.inputParameter = inputParameter;
    }
}
