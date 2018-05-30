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
    @JsonProperty("sharingInformation")
    private Map<String, ResourceSharingInformation> sharingInformation = new HashMap<>();

    /**
     * This value is calculated by Trust Manager of the platform owing the resource and it is propagated to
     * the other platform members
     */
    @JsonProperty("resourceTrust")
    private Double resourceTrust;

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

    public Double getResourceTrust() { return resourceTrust; }
    public void setResourceTrust(Double resourceTrust) { this.resourceTrust = resourceTrust; }
}
