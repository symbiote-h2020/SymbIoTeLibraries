package eu.h2020.symbiote.core.cci;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.List;

/**
 * Response class for describing results of operations on JSON-described resources. Used in responses from Cloud Core Interface's
 * resource registration and modification.
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class ResourceRegistryResponse {

    @JsonProperty("message")
    private String message;

    @JsonProperty("resources")
    private List<Resource> resources;

    public ResourceRegistryResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
