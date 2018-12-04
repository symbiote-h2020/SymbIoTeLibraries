package eu.h2020.symbiote.core.ci;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * Class describing message for performing sparql getResourceUrls.
 */
public class SparqlQueryRequest {
    /** Contents of the sparql query  */
    private String sparqlQuery;
    /** Output format results should be presented with */
    private SparqlQueryOutputFormat outputFormat;
    /** Iri of the base model - specified only if you want to use sparql query rewritting functionality */
    private String baseModel;

    public SparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public SparqlQueryRequest(String sparqlQuery, SparqlQueryOutputFormat outputFormat, String baseModel) {
        setSparqlQuery(sparqlQuery);
        setOutputFormat(outputFormat);
        setBaseModel(baseModel);
    }
    
    public SparqlQueryRequest(String sparqlQuery, SparqlQueryOutputFormat outputFormat) {
        setSparqlQuery(sparqlQuery);
        setOutputFormat(outputFormat);
    }

    public SparqlQueryRequest(SparqlQueryRequest sparqlQueryRequest) {
        setSparqlQuery(sparqlQueryRequest.getSparqlQuery());
        setOutputFormat(sparqlQueryRequest.getOutputFormat());
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

    @JsonIgnore
    public boolean isValid() {
        return sparqlQuery != null && outputFormat != null;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;

        // null check
        if (o == null)
            return false;

        // type check and cast
        if (!(o instanceof SparqlQueryRequest))
            return false;

        SparqlQueryRequest request = (SparqlQueryRequest) o;
        // field comparison
        return Objects.equals(this.getSparqlQuery(), request.getSparqlQuery())
                && Objects.equals(this.getOutputFormat(), request.getOutputFormat());
    }

    public String getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(String baseModel) {
        this.baseModel = baseModel;
    }
}
