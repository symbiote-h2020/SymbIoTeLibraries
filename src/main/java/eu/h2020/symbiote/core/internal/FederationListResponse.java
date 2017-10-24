package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponse;
import eu.h2020.symbiote.model.mim.Federation;

import java.util.List;

/**
 * Payload model used for response in 'get all federations' and 'get federations for platform' requests to Registry.
 *
 * Created by mateuszl on 22.08.2017.
 */
public class FederationListResponse extends AbstractResponse<List<Federation>> {

    public FederationListResponse() {
        // Needed for Jackson serialization
    }

    public FederationListResponse(int status, String message, List<Federation> body) {
        super(status, message, body);
    }
}