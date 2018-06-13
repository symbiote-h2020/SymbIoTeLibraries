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

    private Set<String> deletedFederatedResources;

    /**
     * Construct an instance using the provided arguments.
     *
     * @param deletedFederatedResources a set containing all the federated resource ids
     *                                  which were unshared/deleted
     */
    @JsonCreator
    public ResourcesDeletedMessage(@JsonProperty("deletedFederatedResources")
                                   Set<String> deletedFederatedResources) {
        this.deletedFederatedResources = deletedFederatedResources;
    }

    public Set<String> getDeletedFederatedResources() {
        return deletedFederatedResources;
    }

    public void setDeletedFederatedResources(Set<String> deletedFederatedResources) {
        this.deletedFederatedResources = deletedFederatedResources;
    }
}
