package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/30/2017.
 */
public class ListResourcesRequest {

    @JsonProperty("id")
    private String id;

    public ListResourcesRequest() {
    }

    public ListResourcesRequest(String id) {
        setId(id);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
