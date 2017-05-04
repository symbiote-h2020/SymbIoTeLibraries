package eu.h2020.symbiote.core.ci;

import java.util.List;

/**
 * Response message containing results of basic, JSON based resource query.
 */
public class QueryResponse {
    private List<QueryResourceResult> resources;

    public QueryResponse() {
        //Needed for Jackson serialization
    }

    public List<QueryResourceResult> getResources() {
        return resources;
    }

    public void setResources(List<QueryResourceResult> resources) {
        this.resources = resources;
    }
}
