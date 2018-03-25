package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * CIM-defined Capability class.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class Capability {
    
    @JsonProperty("parameters")
    private List<Parameter> parameters;

    @JsonProperty("effects")
    private List<Effect> effects;
    
    @JsonProperty("name")
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Capability that = (Capability) o;

        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) return false;
        if (effects != null ? !effects.equals(that.effects) : that.effects != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        int result = parameters != null ? parameters.hashCode() : 0;
        result = 31 * result + (effects != null ? effects.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Capability{" +
                "parameters=" + parameters +
                ", effects=" + effects +
                ", name='" + name + '\'' +
                '}';
    }
}
