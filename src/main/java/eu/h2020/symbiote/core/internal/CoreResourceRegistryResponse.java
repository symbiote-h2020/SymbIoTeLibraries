package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 * Payload of responses to internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 * <p>
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryResponse extends AbstractResponseSecured<String> {

    private DescriptionType descriptionType;

    public CoreResourceRegistryResponse() {
        // Needed for Jackson serialization
    }

    public CoreResourceRegistryResponse(int status, String message, String body, DescriptionType descriptionType) {
        super(status, message, body);
        this.descriptionType = descriptionType;
    }

    public DescriptionType getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }
}
