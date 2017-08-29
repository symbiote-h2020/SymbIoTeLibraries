package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 7/18/2017.
 */
public class CancelTaskResponse {

    @JsonProperty("status")
    private CancelTaskResponseStatus status;

    @JsonProperty("message")
    private String message;

    public CancelTaskResponse() {
        // empty constructor
    }

    public CancelTaskResponse(CancelTaskResponseStatus status, String message) {
        setStatus(status);
        setMessage(message);
    }

    public CancelTaskResponseStatus getStatus() { return status; }
    public void setStatus(CancelTaskResponseStatus status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
