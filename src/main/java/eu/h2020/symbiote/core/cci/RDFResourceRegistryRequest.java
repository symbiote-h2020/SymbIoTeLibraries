package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest {

    private RDFInfo rdfInfo;

    private String interworkingServiceUrl;

    public RDFResourceRegistryRequest() {
        // Needed for Jackson serialization
    }

    public RDFInfo getRdfInfo() {
        return rdfInfo;
    }

    public void setRdfInfo(RDFInfo rdfInfo) {
        this.rdfInfo = rdfInfo;
    }

    public String getInterworkingServiceUrl() {
        return interworkingServiceUrl;
    }

    public void setInterworkingServiceUrl(String interworkingServiceUrl) {
        this.interworkingServiceUrl = interworkingServiceUrl;
    }
}
