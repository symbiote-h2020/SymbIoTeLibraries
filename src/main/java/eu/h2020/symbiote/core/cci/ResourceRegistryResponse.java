package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractResponseSecured;
import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.Map;

/**
 * Response class for describing results of operations on JSON-described resources. Used in responses from Cloud Core Interface's
 * resource registration and modification.
 * <p>
 * Created by Szymon Mueller on 30/03/2017.
 */
public class ResourceRegistryResponse extends AbstractResponseSecured<Map<String, Resource>> {

    public ResourceRegistryResponse() {
        // Needed for Jackson serialization
    }

    public ResourceRegistryResponse(int status, String message, Map<String, Resource> body) {
        super(status, message, body);
    }

    public Map<String, Resource> getResources() {
        return super.getBody();
    }

    public void setResources(Map<String, Resource> resources) {
        super.setBody(resources);
    }
}
