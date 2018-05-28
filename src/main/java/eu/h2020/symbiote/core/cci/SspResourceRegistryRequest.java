package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;

import java.util.Map;

/**
 * Class representing resource registry request coming from SSP (SDEV).
 *
 * Created by mateuszl on 25.05.2018.
 */
public class SspResourceRegistryRequest extends AbstractRequest<Map<String,Resource>> {

    private Map<String, IAccessPolicySpecifier> filteringPolicies;

    public Map<String, IAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, IAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }

    public SspResourceRegistryRequest(Map<String,Resource> body) {
        super(body);
    }

    public SspResourceRegistryRequest() {
        // Needed for Jackson serialization
    }
}