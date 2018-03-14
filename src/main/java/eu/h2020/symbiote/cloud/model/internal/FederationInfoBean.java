package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class FederationInfoBean {

    @JsonProperty("symbioteId")
    private String symbioteId;

    /**
     * This map contains information about the federations in which this resource has been shared.
     * The key is the federation ID
     * The value is the information of this resource in that federation such as:
     * date in which it was shared, access policies, etc.
     */
    @JsonProperty("federationInfo")
    private Map<String, ResourceSharingInformation> sharingInformation = new HashMap<>();

    public String getSymbioteId() {
        return symbioteId;
    }

    public void setSymbioteId(String symbioteId) {
        this.symbioteId = symbioteId;
    }

    public Map<String, ResourceSharingInformation> getSharingInformation() {
        return sharingInformation;
    }

    public void setSharingInformation(Map<String, ResourceSharingInformation> sharingInformation) {
        this.sharingInformation = sharingInformation;
    }
}
