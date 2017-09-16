package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.AbstractResponseSecured;
import java.util.HashMap;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * @author Vasileios Glykantzis (ICOM)
 */
public class ResourceUrlsResponse extends AbstractResponseSecured<HashMap<String, String>> {

    public ResourceUrlsResponse() {
        // Needed for Jackson serialization
    }

    public ResourceUrlsResponse(int status, String message, HashMap<String, String> body) {
        super(status, message, body);
    }
}
