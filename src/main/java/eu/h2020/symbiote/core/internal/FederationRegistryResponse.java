package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponse;
import eu.h2020.symbiote.model.mim.Federation;

/**
 * Payload model used in Federations CRUD responses from Registry.
 *
 * Created by mateuszl on 22.08.2017.
 */
public class FederationRegistryResponse extends AbstractResponse<Federation> {

    public FederationRegistryResponse() {
        // Needed for Jackson serialization
    }

    public FederationRegistryResponse(int status, String message, Federation body) {
        super(status, message, body);
    }
}