package eu.h2020.symbiote.core.internal;

/**
 * POJO describing a sparql query for resources.
 */
public class CoreSparqlQueryRequest {
    private String query;
    private String token;

    /**
     * Default constructor.
     */
    public CoreSparqlQueryRequest() {
        // Needed for Jackson serialization
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
