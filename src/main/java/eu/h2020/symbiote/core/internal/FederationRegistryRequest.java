package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.Federation;

/**
 * Payload model used in Federations CRUD requests to Registry.
 *
 * Created by mateuszl on 22.08.2017.
 */
public class FederationRegistryRequest {

    private String token;
    private Federation federation;

    public FederationRegistryRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Federation getFederation() {
        return federation;
    }

    public void setFederation(Federation federation) {
        this.federation = federation;
    }
}