package eu.h2020.symbiote.core.ci;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

import java.util.ArrayList;
import java.util.List;

/**
 * Response message containing results of basic, JSON based resource query.
 */
public class QueryResponse extends AbstractResponseSecured<List<QueryResourceResult>>{

    public QueryResponse() {
        //Needed for Jackson serialization
    }

    public QueryResponse(int status, String message, List<QueryResourceResult> body) {
        super(status, message, body);
    }
}
