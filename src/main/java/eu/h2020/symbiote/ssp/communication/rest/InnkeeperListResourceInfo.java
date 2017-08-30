package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vasgl on 8/24/2017.
 */
public class InnkeeperListResourceInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private InnkeeperResourceStatus status;

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public InnkeeperListResourceInfo() {
        // empty constructor
    }

    public InnkeeperListResourceInfo(String id, String name, String description,
                                     InnkeeperResourceStatus status, List<String> observesProperty) {
        setId(id);
        setName(name);
        setDescription(description);
        setStatus(status);
        setObservesProperty(observesProperty);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public InnkeeperResourceStatus getStatus() { return status; }
    public void setStatus(InnkeeperResourceStatus status) { this.status = status; }

    public List<String> getObservesProperty() { return observesProperty; }
    public void setObservesProperty(List<String> observesProperty) { this.observesProperty = observesProperty; }
}
