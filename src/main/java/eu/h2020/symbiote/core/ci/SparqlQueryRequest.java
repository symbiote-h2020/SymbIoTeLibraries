package eu.h2020.symbiote.core.ci;

/**
 * Class describing message for performing sparql query.
 */
public class SparqlQueryRequest {
    private String sparqlQuery;
    private SparqlQueryOutputFormat outputFormat;

    public SparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public String getSparqlQuery() {
        return sparqlQuery;
    }

    public void setSparqlQuery(String sparqlQuery) {
        this.sparqlQuery = sparqlQuery;
    }

    public SparqlQueryOutputFormat getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(SparqlQueryOutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }
}
