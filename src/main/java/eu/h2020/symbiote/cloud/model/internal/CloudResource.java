package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.h2020.symbiote.cloud.model.CloudResourceParams;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class CloudResource  {

    @Id
    @JsonProperty("internalId")
    private String internalId;
    @JsonProperty("pluginId")
    private String pluginId;

    //ip address of the host. Needed by Monitoring component
    @JsonProperty("cloudMonitoringHost")
    private String cloudMonitoringHost;

    @JsonProperty("accessPolicy")
    private IAccessPolicySpecifier accessPolicy;
    @JsonProperty("filteringPolicy")
    private IAccessPolicySpecifier filteringPolicy;

    @JsonProperty("resource")
    //For backwards compatibility, core registration remains here
    Resource resource;

    @JsonProperty("federationIds")
    private Map<String, ResourceSharingInformation> federationInfo = new HashMap<>();

    //Needed by Monitoring component
    @JsonProperty("params")
    CloudResourceParams params;

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

    public String getCloudMonitoringHost() {
        return cloudMonitoringHost;
    }

    public void setCloudMonitoringHost(String cloudMonitoringHost) {
        this.cloudMonitoringHost = cloudMonitoringHost;
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

    public Map<String, ResourceSharingInformation> getFederationInfo() {
        return federationInfo;
    }

    public void setFederationInfo(Map<String, ResourceSharingInformation> federationInfo) {
        this.federationInfo = federationInfo;
    }

    public CloudResourceParams getParams() {
        return params;
    }

    public void setParams(CloudResourceParams params) {
        this.params = params;
    }
}
