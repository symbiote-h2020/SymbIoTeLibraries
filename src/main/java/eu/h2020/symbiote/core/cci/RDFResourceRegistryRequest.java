package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequest;
import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 * <p>
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest extends AbstractRequest<RDFInfo> {

    public RDFResourceRegistryRequest(String token, RDFInfo body, String interworkingServiceUrl) {
        super(token, body);
        this.interworkingServiceUrl = interworkingServiceUrl;
    }

    public RDFResourceRegistryRequest() {
    }

    private String interworkingServiceUrl;

    public RDFInfo getRdfInfo() {
        return super.getBody();
    }

    public void setRdfInfo(RDFInfo rdfInfo) {
        super.setBody(rdfInfo);
    }

    public String getInterworkingServiceUrl() {
        return interworkingServiceUrl;
    }

    public void setInterworkingServiceUrl(String interworkingServiceUrl) {
        this.interworkingServiceUrl = interworkingServiceUrl;
    }
}
