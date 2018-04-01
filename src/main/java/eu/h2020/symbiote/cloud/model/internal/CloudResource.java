package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.core.annotations.QueryEntity;
import eu.h2020.symbiote.cloud.model.CloudResourceParams;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@QueryEntity
@Document
public class CloudResource  {

    @Id
    @JsonProperty("internalId")
    private String internalId;
    @JsonProperty("pluginId")
    private String pluginId;

    @JsonProperty("accessPolicy")
    private IAccessPolicySpecifier accessPolicy;
    @JsonProperty("filteringPolicy")
    private IAccessPolicySpecifier filteringPolicy;

    @JsonProperty("resource")
    //For backwards compatibility, core registration remains here
    private Resource resource;

    @JsonProperty("federationInfo")
    private FederationInfoBean federationInfo;

    public CloudResource() {
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getPluginId() {
        return pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public IAccessPolicySpecifier getAccessPolicy() {
        return accessPolicy;
    }

    public void setAccessPolicy(IAccessPolicySpecifier accessPolicySpecifier) {
        this.accessPolicy = accessPolicySpecifier;
    }

    public IAccessPolicySpecifier getFilteringPolicy() {
        return filteringPolicy;
    }

    public void setFilteringPolicy(IAccessPolicySpecifier filteringPolicySpecifier) {
        this.filteringPolicy = filteringPolicySpecifier;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public FederationInfoBean getFederationInfo() { return federationInfo; }

    public void setFederationInfo(FederationInfoBean federationInfo) { this.federationInfo = federationInfo; }
}
