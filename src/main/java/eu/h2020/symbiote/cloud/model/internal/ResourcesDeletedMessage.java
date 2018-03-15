package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;

/**
 * This class is used for information exchange between Platform Registry and Subscription Manager regarding
 * deletion of federated platform resources
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/21/2018.
 */
public class ResourcesDeletedMessage {

    private Map<String, Set<String>> deletedFederatedResourcesMap;

    /**
     * Construct an instance using the provided arguments.
     *
     * @param deletedFederatedResourcesMap a map in which the id is the federatedResource id and the value
     *                                     the federation ids in which the resource was unshared
     */
    @JsonCreator
    public ResourcesDeletedMessage(@JsonProperty(value = "deletedFederatedResourcesMap")
                                               Map<String, Set<String>> deletedFederatedResourcesMap) {
        this.deletedFederatedResourcesMap = deletedFederatedResourcesMap;
    }


    public Map<String, Set<String>> getDeletedFederatedResourcesMap() { return deletedFederatedResourcesMap; }
    public void setDeletedFederatedResourcesMap(Map<String, Set<String>> deletedFederatedResourcesMap) {
        this.deletedFederatedResourcesMap = deletedFederatedResourcesMap;
    }
}
