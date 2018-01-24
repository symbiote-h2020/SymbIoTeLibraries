package eu.h2020.symbiote.core.cci;


import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;

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
public class ResourceRegistryRequest extends AbstractRequest<Map<String, Resource>> {


    private Map<String, IAccessPolicySpecifier> filteringPolicies;

    public ResourceRegistryRequest(Map<String, Resource> body) {
        super(body);
    }

    public ResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    public Map<String, IAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, IAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }
}
