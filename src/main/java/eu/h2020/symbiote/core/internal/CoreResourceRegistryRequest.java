package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Payload of internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 * <p>
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryRequest extends AbstractRequestSecured<String> {

    private DescriptionType descriptionType;
    private String platformId;

    public CoreResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    /**
     * @param securityRequest
     * @param body
     * @param descriptionType
     */
    public CoreResourceRegistryRequest(SecurityRequest securityRequest, String body, DescriptionType descriptionType, String platformId) {
        super(securityRequest, body);
        this.descriptionType = descriptionType;
        this.platformId = platformId;
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

    public String getBody() {
        return super.getBody();
    }

    public void setBody(String body) {
        super.setBody(body);
    }
}
