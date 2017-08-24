package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/24/2017.
 */
public class KeepAliveResponse {

    @JsonProperty("result")
    private String result;

    public KeepAliveResponse() {
        // empty constructor
    }

    public KeepAliveResponse(String result) {
        setResult(result);
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
