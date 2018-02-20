package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * This class is used for information exchange between Platform Registry and Subscription Manager regarding
 * deletion of federated platform resources
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/21/2018.
 */
public class ResourcesDeletedMessage {

    private List<String> deletedIds;

    /**
     * Construct an instance using the provided arguments.
     *
     * @param deletedIds a list of federation ids of the newly deleted resources
     */
    @JsonCreator
    public ResourcesDeletedMessage(@JsonProperty(value = "deletedIds") List<String> deletedIds) {
        this.deletedIds = deletedIds;
    }

    public List<String> getDeletedIds() { return deletedIds; }
    public void setDeletedIds(List<String> deletedIds) { this.deletedIds = deletedIds; }
}
