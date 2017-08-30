package eu.h2020.symbiote.ssp.communication.rabbit;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/30/2017.
 */
public class SSPResourceDeleted {

    @JsonProperty("id")
    private String id;

    public SSPResourceDeleted() {
    }

    public SSPResourceDeleted(String id) {
        setId(id);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
