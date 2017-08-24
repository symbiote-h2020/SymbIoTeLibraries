package eu.h2020.symbiote.ssp.innkeeper.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vasgl on 8/24/2017.
 */
public class InnkeeperListResourceInfo {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private InnkeeperResourceStatus status;

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public InnkeeperListResourceInfo() {
        // empty constructor
    }

    public InnkeeperListResourceInfo(String id, InnkeeperResourceStatus status, List<String> observesProperty) {
        setId(id);
        setStatus(status);
        setObservesProperty(observesProperty);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public InnkeeperResourceStatus getStatus() { return status; }
    public void setStatus(InnkeeperResourceStatus status) { this.status = status; }

    public List<String> getObservesProperty() { return observesProperty; }
    public void setObservesProperty(List<String> observesProperty) { this.observesProperty = observesProperty; }
}
