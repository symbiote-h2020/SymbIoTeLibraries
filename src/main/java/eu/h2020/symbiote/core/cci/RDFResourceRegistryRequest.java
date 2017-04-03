package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest extends RDFInfo {

    private String token;

    public RDFResourceRegistryRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
