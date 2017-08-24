package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/23/2017.
 */
public class JoinResponse {

    @JsonProperty("result")
    private JoinResponseResult result;

    @JsonProperty("id")
    private String id;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("registrationExpiration")
    private Integer registrationExpiration;

    public JoinResponse() {
        // empty constructor
    }

    public JoinResponse(JoinResponseResult result, String id, String hash, Integer registrationExpiration) {
        setResult(result);
        setId(id);
        setHash(hash);
        setRegistrationExpiration(registrationExpiration);
    }

    public JoinResponseResult getResult() { return result; }
    public void setResult(JoinResponseResult result) { this.result = result; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public Integer getRegistrationExpiration() {
        return registrationExpiration;
    }
    public void setRegistrationExpiration(Integer registrationExpiration) {
        this.registrationExpiration = registrationExpiration;
    }
}
