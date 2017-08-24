package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/24/2017.
 */
public class KeepAliveRequest {

    @JsonProperty("id")
    private String id;

    public KeepAliveRequest() {
        // empty constructor
    }

    public KeepAliveRequest(String id) {
        setId(id);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
