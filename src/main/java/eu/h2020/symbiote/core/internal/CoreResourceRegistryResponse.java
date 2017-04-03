package eu.h2020.symbiote.core.internal;

/**
 * Payload of responses to internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 *
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryResponse {

    /**
     * HTTP status of the request
     */
    private int status;

    private String message;

    private DescriptionType descriptionType;

    private String body;

    public CoreResourceRegistryResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DescriptionType getDescriptionType() {
        return descriptionType;
    }

    public void setDescriptionType(DescriptionType descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
