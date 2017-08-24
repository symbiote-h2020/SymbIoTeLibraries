package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.Federation;

import java.util.List;

/**
 * Payload model used for response in 'get all federations' and 'get federations for platform' requests to Registry.
 *
 * Created by mateuszl on 22.08.2017.
 */
public class FederationListResponse extends StatusResponse<List<Federation>> {

    public FederationListResponse() {
    }

    public FederationListResponse(int status, String message, List<Federation> body) {
        super(status, message, body);
    }

    public List<Federation> getFederations() {
        return super.getBody();
    }

    public void setFederations(List<Federation> federations) {
        super.setBody(federations);
    }
}