package eu.h2020.symbiote.core.ci;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 * Sparql Query secured response.
 *
 * Created by Szymon Mueller on 02/11/2017.
 */
public class SparqlQueryResponse  extends AbstractResponseSecured<String> {

    public SparqlQueryResponse() {
    }

    public SparqlQueryResponse(int status, String message, String body) {
        super(status, message, body);
    }
}
