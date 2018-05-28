package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

import java.util.Map;

/**
 * Created by Szymon Mueller on 28/05/2018.
 */
public class CoreSspResourceRegistryRequest extends AbstractRequestSecured<Map<String, Resource>> {

    private String sspId;
    private String sdevId;

    private Map<String, IAccessPolicySpecifier> filteringPolicies;


    public CoreSspResourceRegistryRequest() {
        //Empty constructor
    }

    public CoreSspResourceRegistryRequest(SecurityRequest securityRequest, Map<String, Resource> resources, String sspId, String sdevId,
                                          Map<String, IAccessPolicySpecifier> filteringPolicies) {
        super(securityRequest, resources);
        this.sspId = sspId;
        this.sdevId = sdevId;
        this.filteringPolicies = filteringPolicies;
    }

    public String getSspId() {
        return sspId;
    }

    public void setSspId(String sspId) {
        this.sspId = sspId;
    }

    public String getSdevId() {
        return sdevId;
    }

    public void setSdevId(String sdevId) {
        this.sdevId = sdevId;
    }

    public Map<String, IAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, IAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }
}
