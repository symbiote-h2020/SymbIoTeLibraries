package eu.h2020.symbiote.core.internal;

/**
 * Payload of internal Core communication message for registry operation on resources (both RDF and JSON).
 * Used in communication between CloudCoreInterface and Registry.
 *
 * Created by Szymon Mueller on 31/03/2017.
 */
public class CoreResourceRegistryRequest {

    private String token;

    private DescriptionType descriptionType;

    private String body;

    public CoreResourceRegistryRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
