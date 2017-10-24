package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import eu.h2020.symbiote.cloud.model.CloudResourceParams;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.singletoken.SingleTokenAccessPolicySpecifier;

import org.springframework.data.annotation.Id;

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
    @JsonProperty("singleTokenAccessPolicy")
    private SingleTokenAccessPolicySpecifier singleTokenAccessPolicy;
    @JsonProperty("singleTokenFilteringPolicy")
    private SingleTokenAccessPolicySpecifier singleTokenFilteringPolicy;
    @JsonProperty("resource")
    Resource resource;

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
    
    public void setSingleTokenAccessPolicy(SingleTokenAccessPolicySpecifier singleToken) {
        this.singleTokenAccessPolicy = singleToken;
    }
    
    public SingleTokenAccessPolicySpecifier getSingleTokenAccessPolicy() {
        return singleTokenAccessPolicy;
    }
    
    public SingleTokenAccessPolicySpecifier getSingleTokenFilteringPolicy() {
        return singleTokenFilteringPolicy;
    }
    
    public void setSingleTokenFilteringPolicy(SingleTokenAccessPolicySpecifier singleTokenFilteringPolicy) {
        this.singleTokenFilteringPolicy = singleTokenFilteringPolicy;
    }
    
    public Resource getResource() {
            return resource;
    }

    public void setResource(Resource resource) {
            this.resource = resource;
    }

    public CloudResourceParams getParams() {
            return params;
    }

    public void setParams(CloudResourceParams params) {
            this.params = params;
    }  
    
}
