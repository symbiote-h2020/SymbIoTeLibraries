package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class representing CIM feature of interest class.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
public class FeatureOfInterest {

    @JsonProperty("name")
    private List<String> name;
    @JsonProperty("description")
    private List<String> description;
    @JsonProperty("hasProperty")
    private List<String> hasProperty;

    public FeatureOfInterest() {
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<String> getHasProperty() {
        return hasProperty;
    }

    public void setHasProperty(List<String> hasProperty) {
        this.hasProperty = hasProperty;
    }
}
