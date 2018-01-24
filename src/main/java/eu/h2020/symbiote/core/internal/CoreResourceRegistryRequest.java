package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

import java.util.Map;

/**
 * Payload of internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 * <p>
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryRequest extends AbstractRequestSecured<String> {

    private DescriptionType descriptionType;
    private String platformId;

    private Map<String, IAccessPolicySpecifier> filteringPolicies;

    public CoreResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    public CoreResourceRegistryRequest(SecurityRequest securityRequest, String body, DescriptionType descriptionType, String platformId, Map<String, IAccessPolicySpecifier> filteringPolicies) {
        super(securityRequest, body);
        this.descriptionType = descriptionType;
        this.platformId = platformId;
        this.filteringPolicies = filteringPolicies;
    }

    public DescriptionType getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Map<String, IAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, IAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }
}
