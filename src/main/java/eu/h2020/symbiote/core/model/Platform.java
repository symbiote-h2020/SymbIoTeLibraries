package eu.h2020.symbiote.core.model;

import org.springframework.data.annotation.Id;

import java.util.List;


public class Platform extends RDFInfo {

    @Id
    private String id;
    private List<String> labels;
    private List<String> comments;
    private List<InterworkingService> interworkingServices;

    public Platform() {

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

    public List<InterworkingService> getInterworkingServices() {
        return interworkingServices;
    }

    public void setInterworkingServices(List<InterworkingService> interworkingServices) {
        this.interworkingServices = interworkingServices;
    }
}