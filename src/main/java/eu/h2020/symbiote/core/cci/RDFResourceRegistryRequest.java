package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.internal.RDFInfo;
import eu.h2020.symbiote.security.accesspolicies.common.singletoken.SingleTokenAccessPolicySpecifier;

import java.util.Map;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 *
 * Specifies url of the interworking service that handles resources defined in the RDF.
 *
 * Specifies filtering policies for specified resources. Keys of the filteringPolicies map are pairingIds of the
 * resources, and values are
 *
 * <p>
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest extends AbstractRequest<RDFInfo> {

    private String interworkingServiceUrl;

    private Map<String,SingleTokenAccessPolicySpecifier> filteringPolicies;

    public RDFResourceRegistryRequest(RDFInfo body, String interworkingServiceUrl) {
        super(body);
        this.interworkingServiceUrl = interworkingServiceUrl;
    }

    public RDFResourceRegistryRequest() {
    }

    public String getInterworkingServiceUrl() {
        return interworkingServiceUrl;
    }

    public void setInterworkingServiceUrl(String interworkingServiceUrl) {
        this.interworkingServiceUrl = interworkingServiceUrl;
    }

    public Map<String, SingleTokenAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, SingleTokenAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }
}
