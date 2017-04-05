package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Response class for describing results of operations on RDF-described resources. Used in responses from Cloud Core Interface's
 * resource registration and modification.
 *
 * Created by Szymon Mueller on 30/03/2017.
 */
public class RDFResourceRegistryResponse extends RDFInfo {

    private String message;

    public RDFResourceRegistryResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
