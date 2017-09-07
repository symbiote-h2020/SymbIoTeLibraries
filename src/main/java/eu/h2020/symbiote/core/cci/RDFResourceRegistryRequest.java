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

    public RDFResourceRegistryRequest(RDFInfo body, String interworkingServiceUrl) {
        super(body);
        this.interworkingServiceUrl = interworkingServiceUrl;
    }

    public RDFResourceRegistryRequest() {
    }

    private String interworkingServiceUrl;

    public String getInterworkingServiceUrl() {
        return interworkingServiceUrl;
    }

    public void setInterworkingServiceUrl(String interworkingServiceUrl) {
        this.interworkingServiceUrl = interworkingServiceUrl;
    }
}
