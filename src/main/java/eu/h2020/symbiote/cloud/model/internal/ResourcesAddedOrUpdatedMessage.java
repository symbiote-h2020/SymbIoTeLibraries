package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class is used for information exchange between Platform Registry and Subscription Manager regarding
 * registration or update of federated platform resources
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/21/2018.
 */
public class ResourcesAddedOrUpdatedMessage {
    private List<FederatedResource> newFederatedResources;

    /**
     * Construct an instance using the provided arguments.
     *
     * @param newFederatedResources a list of the newly registered or updated federated resources
     */
    @JsonCreator
    public ResourcesAddedOrUpdatedMessage(@JsonProperty(value = "newFederatedResources") List<FederatedResource> newFederatedResources) {
        this.newFederatedResources = newFederatedResources;
    }

    public List<FederatedResource> getNewFederatedResources() {
        return newFederatedResources;
    }

    public void setNewFederatedResources(List<FederatedResource> newFederatedResources) {
        this.newFederatedResources = newFederatedResources;
    }
}
