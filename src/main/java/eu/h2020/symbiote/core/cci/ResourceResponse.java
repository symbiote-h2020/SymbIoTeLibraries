package eu.h2020.symbiote.core.cci;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Resource;

/**
 * Describes the registry operation (registration, modification) result for particular resource. Contains information
 * if resource description is valid as well as resource description containing symbioteId of the resource.
 *
 * Created by Szymon Mueller on 31/03/2017.
 */
@Deprecated
public class ResourceResponse {

    /**
     * Information if resource description has been validated successfully.
     */
    @JsonProperty("valid")
    private boolean valid;

    /**
     * Contains detailed message from the Core about the resource registry operation (e.g. contains detailed explanation
     * why validation failed).
     */
    @JsonProperty("message")
    private String message;

    /**
     * Resource description - contains symbioteId.
     */
    @JsonProperty("resource")
    private Resource resource;

    public ResourceResponse() {
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
