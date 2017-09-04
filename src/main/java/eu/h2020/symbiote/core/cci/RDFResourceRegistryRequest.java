package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.core.model.RDFInfo;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Request class for operations on RDF-described resources. Used as a payload for Cloud Core Interface's
 * resource registration and modification.
 * <p>
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryRequest extends AbstractRequestSecured<RDFInfo> {

    public RDFResourceRegistryRequest(SecurityRequest securityRequest, RDFInfo body, String interworkingServiceUrl) {
        super(securityRequest, body);
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
