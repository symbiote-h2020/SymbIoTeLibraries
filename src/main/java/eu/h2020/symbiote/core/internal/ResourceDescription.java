package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.RDFInfo;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymon Mueller on 15/03/2017.
 */
public class ResourceDescription extends RDFInfo {

    private String id;
    private List<String> labels = new ArrayList<>();
    private List<String> comments = new ArrayList<>();
    private String interworkingServiceURL;

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

    public String getInterworkingServiceURL() {
        return interworkingServiceURL;
    }

    public void setInterworkingServiceURL(String interworkingServiceURL) {
        this.interworkingServiceURL = interworkingServiceURL;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
