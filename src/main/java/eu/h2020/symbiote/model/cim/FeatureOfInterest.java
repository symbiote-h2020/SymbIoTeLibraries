package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class representing CIM feature of interest class.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
public class FeatureOfInterest {

    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("comments")
    private List<String> comments;
    @JsonProperty("hasProperty")
    private List<String> hasProperty;

    public FeatureOfInterest() {
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getHasProperty() {
        return hasProperty;
    }

    public void setHasProperty(List<String> hasProperty) {
        this.hasProperty = hasProperty;
    }
}
