package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.model.AbstractRequest;

/**
 * POJO describing a sparql query for resources.
 */
public class CoreSparqlQueryRequest extends AbstractRequest<String> {
    private SparqlQueryOutputFormat outputFormat;

    /**
     * Default constructor.
     */
    public CoreSparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public CoreSparqlQueryRequest(String body, SparqlQueryOutputFormat outputFormat) {
        super(body);
        this.outputFormat = outputFormat;
    }

    public String getQuery() {
        return super.getBody();
    }

    public void setQuery(String query) {
        this.setBody(query);
    }

    public SparqlQueryOutputFormat getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(SparqlQueryOutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }
}
