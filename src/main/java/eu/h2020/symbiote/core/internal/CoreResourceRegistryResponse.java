package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.AbstractResponseSecured;

/**
 * Payload of responses to internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 *
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryResponse extends AbstractResponseSecured<String> {

    private DescriptionType descriptionType;

    private String body;

    public CoreResourceRegistryResponse() {
        // Needed for Jackson serialization
    }

    public CoreResourceRegistryResponse(int status, String message, String body, DescriptionType descriptionType, String body1) {
        super(status, message, body);
        this.descriptionType = descriptionType;
        this.body = body1;
    }

    public DescriptionType getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getBody() {
        return super.getBody();
    }

    public void setBody(String body) {
        super.setBody(body);
    }
}
