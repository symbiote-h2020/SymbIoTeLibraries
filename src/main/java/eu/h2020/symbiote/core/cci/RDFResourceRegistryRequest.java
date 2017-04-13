package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest {

    private String token;

    private RDFInfo rdfInfo;

    public RDFResourceRegistryRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RDFInfo getRdfInfo() {
        return rdfInfo;
    }

    public void setRdfInfo(RDFInfo rdfInfo) {
        this.rdfInfo = rdfInfo;
    }
}
