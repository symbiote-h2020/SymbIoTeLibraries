package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * CIM-defined Capability class.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class Capability {

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("parameters")
    private List<Parameter> parameters;

    @JsonProperty("effects")
    private List<Effect> effects;
    
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }   
    
    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
