package eu.h2020.symbiote.core.cci;


import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.core.model.resources.Resource;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

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
public class ResourceRegistryRequest extends AbstractRequestSecured<Map<String, Resource>> {

    public ResourceRegistryRequest(SecurityRequest securityRequest, Map<String, Resource> body) {
        super(securityRequest, body);
    }

    public ResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    public Map<String, Resource> getResources() {
        return super.getBody();
    }

    public void setResources(Map<String, Resource> resources) {
        super.setBody(resources);
    }
}
