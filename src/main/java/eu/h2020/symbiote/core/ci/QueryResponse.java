package eu.h2020.symbiote.core.ci;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

import java.util.ArrayList;
import java.util.List;

/**
 * Response message containing results of basic, JSON based resource getResourceUrls.
 */
public class QueryResponse extends AbstractResponseSecured<List<QueryResourceResult>>{

    public QueryResponse() {
        //Needed for Jackson serialization
    }

    public QueryResponse(int status, String message, List<QueryResourceResult> body) {
        super(status, message, body);
    }

    @JsonIgnore
    public List<QueryResourceResult> getResources() {
        return getBody();
    }

}
