package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Subclass of CloudResource for specifying the federations where the resource will be exposed as well as if it is
 * bartered or not.
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/20/2018.
 */
public class FederatedCloudResource extends CloudResource {

    @JsonProperty("federationBarteredResourceMap")
    private Map<String, Boolean> federationBarteredResourceMap;

    public Map<String, Boolean> getFederationBarteredResourceMap() {
        return federationBarteredResourceMap;
    }

    public void setFederationBarteredResourceMap(Map<String, Boolean> federationBarteredResourceMap) {
        this.federationBarteredResourceMap = federationBarteredResourceMap;
    }
}
