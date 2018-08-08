package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponse;
import eu.h2020.symbiote.model.mim.OntologyMapping;

import java.util.Set;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * Created by mateuszl on 09.08.2017.
 */
public class MappingListResponse extends AbstractResponse<Set<OntologyMapping>> {

    public MappingListResponse() {
        // Needed for Jackson serialization
    }

    public MappingListResponse(int status, String message, Set<OntologyMapping> body) {
        super(status, message, body);
    }
}
