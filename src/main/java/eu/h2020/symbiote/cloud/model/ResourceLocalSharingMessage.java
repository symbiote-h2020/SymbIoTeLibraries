package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class used to send sharing and unsharing information by RabbitMQ.
 * It's created at Registration Handler side and then passed to the ${rh.exchange.name} exchange.
 * When it's passed by ${rh.resource.local.shared} key, it contains shared resources.
 * When using ${rh.resource.local.unshared} key, it contains unshared resources
 */
public class ResourceLocalSharingMessage {

    /**
     * Map with sharing or unsharing information. The key is always the federationId
     * When it's passed by ${rh.resource.local.shared} key, the values are a list of resources which have been shared with the federationId in the key.
     * When it's passed by ${rh.resource.local.unshared} key, the values are a list of resources which have been removed from the federationId in the key.
     */
    private Map<String, List<CloudResource>> sharingMap = new HashMap<>();

    public ResourceLocalSharingMessage() {
    }

    public ResourceLocalSharingMessage(Map<String, List<CloudResource>> sharingMap) {
        this.sharingMap = sharingMap;
    }

    public Map<String, List<CloudResource>> getSharingMap() {
        return sharingMap;
    }

    public void setSharingMap(Map<String, List<CloudResource>> sharingMap) {
        this.sharingMap = sharingMap;
    }
}
