package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for returning Search results from Platform Registry
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/22/2018.
 */
public class FederationSearchResult {
    private List<FederatedResource> resources;

    public FederationSearchResult() {
        resources = new ArrayList<>();
    }

    @JsonCreator
    public FederationSearchResult(@JsonProperty("resources") List<FederatedResource> resources) {
        if (resources != null)
            this.resources = resources;
        else
            this.resources = new ArrayList<>();
    }

    public List<FederatedResource> getResources() { return resources; }
    public void setResources(List<FederatedResource> resources) { this.resources = resources; }

    public void addFederationResourceResult(FederatedResource federatedResource) {
        resources.add(federatedResource);
    }
}