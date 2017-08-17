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
    @JsonProperty("resultType")
    private Datatype resultType;
    @JsonProperty("parameters")
    private List<Parameter> parameters;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Datatype getResultType() {
        return resultType;
    }

    public void setResultType(Datatype resultType) {
        this.resultType = resultType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
