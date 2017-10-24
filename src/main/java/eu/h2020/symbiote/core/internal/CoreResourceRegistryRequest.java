package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.accesspolicies.common.singletoken.SingleTokenAccessPolicySpecifier;
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

    private Map<String,SingleTokenAccessPolicySpecifier> filteringPolicies;

    public CoreResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    /**
     * @param securityRequest
     * @param body
     * @param descriptionType
     */
    public CoreResourceRegistryRequest(SecurityRequest securityRequest, String body, DescriptionType descriptionType, String platformId, Map<String,SingleTokenAccessPolicySpecifier> filteringPolicies ) {
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

    public Map<String, SingleTokenAccessPolicySpecifier> getFilteringPolicies() {
        return filteringPolicies;
    }

    public void setFilteringPolicies(Map<String, SingleTokenAccessPolicySpecifier> filteringPolicies) {
        this.filteringPolicies = filteringPolicies;
    }
}
