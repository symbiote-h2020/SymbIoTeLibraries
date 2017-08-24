package eu.h2020.symbiote.core.cci;


import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.Map;

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

    //TOKEN ??

    @JsonProperty("resources")
    private Map<String, Resource> resources;

    public ResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    public Map<String, Resource> getResources() {
        return resources;
    }

    public void setResources(Map<String, Resource> resources) {
        this.resources = resources;
    }
}
