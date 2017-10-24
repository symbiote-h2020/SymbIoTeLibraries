package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * POJO describing a sparql query for resources.
 */
public class CoreSparqlQueryRequest extends AbstractRequestSecured<String> {

    private SparqlQueryOutputFormat outputFormat;

    /**
     * Default constructor.
     */
    public CoreSparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public CoreSparqlQueryRequest(SecurityRequest securityRequest, String body, SparqlQueryOutputFormat outputFormat) {
        super(securityRequest, body);
        this.outputFormat = outputFormat;
    }

    public SparqlQueryOutputFormat getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(SparqlQueryOutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }
}
