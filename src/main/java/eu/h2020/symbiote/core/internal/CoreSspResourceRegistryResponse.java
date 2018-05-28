package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;
import eu.h2020.symbiote.model.cim.Resource;

import java.util.Map;

/**
 * Created by Szymon Mueller on 28/05/2018.
 */
public class CoreSspResourceRegistryResponse extends AbstractResponseSecured<Map<String, Resource>> {

    public CoreSspResourceRegistryResponse() {
        //Empty constructor
    }

    public CoreSspResourceRegistryResponse(int status, String message, Map<String,Resource> resources) {
        super(status,message,resources);
    }

}
