package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * POJO describing a sparql getResourceUrls for resources.
 */
public class CoreSparqlQueryRequest extends AbstractRequestSecured<String> {

    private SparqlQueryOutputFormat outputFormat;

    /** Iri of the base model - specified only if you want to use sparql query rewritting functionality */
    private String baseModel;

    /**
     * Default constructor.
     */
    public CoreSparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public CoreSparqlQueryRequest(SecurityRequest securityRequest, String body, SparqlQueryOutputFormat outputFormat, String baseModel) {
        super(securityRequest, body);
        this.outputFormat = outputFormat;
        this.baseModel = baseModel;
    }

    public SparqlQueryOutputFormat getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(SparqlQueryOutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(String baseModel) {
        this.baseModel = baseModel;
    }
}
