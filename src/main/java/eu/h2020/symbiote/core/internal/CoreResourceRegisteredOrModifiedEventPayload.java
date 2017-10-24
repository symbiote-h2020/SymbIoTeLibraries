package eu.h2020.symbiote.core.internal;

import java.util.List;

/**
 * Payload used to inform Core system about new resources appearing in the registry (or that resources were modified).
 * Should be consumed by Search, CRAM and other components that need the information about the resources in the system.
 *
 * Created by Szymon Mueller on 18/04/2017.
 */
public class CoreResourceRegisteredOrModifiedEventPayload {

    //Id of the platform that is the owner of the resources
    private String platformId;

    //List of resources containing important resource info: Id, interworking service URL and RDF
    private List<CoreResource> resources;

    public CoreResourceRegisteredOrModifiedEventPayload() {
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public List<CoreResource> getResources() {
        return resources;
    }

    public void setResources(List<CoreResource> resources) {
        this.resources = resources;
    }
}
