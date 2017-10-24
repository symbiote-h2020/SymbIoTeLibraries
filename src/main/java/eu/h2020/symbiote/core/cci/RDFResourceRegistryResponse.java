package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.internal.RDFInfo;

/**
 * Response class for describing results of operations on RDF-described resources. Used in responses from Cloud Core Interface's
 * resource registration and modification.
 * <p>
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryResponse extends AbstractResponse<RDFInfo> {

    public RDFResourceRegistryResponse() {
        // Needed for Jackson serialization
    }

    public RDFResourceRegistryResponse(int status, String message, RDFInfo body) {
        super(status, message, body);
    }
}
