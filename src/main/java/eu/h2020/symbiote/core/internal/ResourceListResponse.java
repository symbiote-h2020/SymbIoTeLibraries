package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;
import eu.h2020.symbiote.model.cim.Resource;

import java.util.List;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * Created by mateuszl on 25.07.2017.
 */
public class ResourceListResponse extends AbstractResponseSecured<List<Resource>> {

    public ResourceListResponse() {
        // Needed for Jackson serialization
    }

    public ResourceListResponse(int status, String message, List<Resource> body) {
        super(status, message, body);
    }
}
