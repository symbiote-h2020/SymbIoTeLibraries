package eu.h2020.symbiote.core.cci;


import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.List;

/**
 * Request class for operations on JSON-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 *
 * Request consists of a token which represents the issuer of the request as well as a list of {@link Resource}
 * objects you want to perform registry operations on (registration, modification).
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class ResourceRegistryRequest {

    @JsonProperty("token")
    private String token;

    @JsonProperty("resources")
    private List<Resource> resources;

    public ResourceRegistryRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
