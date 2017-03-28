package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * This is the most generic resource representation. Each specific resource type from CIM (StationarySensor, Actuator etc) should extend this class.
 *
 * Each resource, regardless of the type (CIM-defined or even PIM-defined), when stored in the Core is represented by this object.
 *
 * Created by Mael on 28/03/2017.
 */
public class Resource {

    private String id;
    private List<String> labels;
    private List<String> comments;
    private String hasInterworkingServiceURL;

    public Resource() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHasInterworkingServiceURL() {
        return hasInterworkingServiceURL;
    }

    public void setHasInterworkingServiceURL(String hasInterworkingServiceURL) {
        this.hasInterworkingServiceURL = hasInterworkingServiceURL;
    }
}
