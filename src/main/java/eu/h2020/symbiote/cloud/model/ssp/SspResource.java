package eu.h2020.symbiote.cloud.model.ssp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;


public class SspResource {
    @org.springframework.data.annotation.Id

    @JsonProperty("internalIdResource")
    private String internalIdResource; //resource Internal Id

    @JsonProperty("sspIdResource")
    private String sspIdResource; // Resource SSP Id

    @JsonProperty("sspIdParent")
    private String sspIdParent; // SDEV SSP Id

    @JsonProperty("symIdParent")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String symIdParent;  // SDEV Symbiote ID

    @JsonProperty("accessPolicy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IAccessPolicySpecifier accessPolicy;
    @JsonProperty("filteringPolicy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private IAccessPolicySpecifier filteringPolicy;
    @JsonProperty("resource")
    private Resource resource; //Sensor, Actuator...


    public SspResource() {
    }

    public String getInternalIdResource() {
        return this.internalIdResource;
    }

    public void setInternalIdResource(String internalIdResource) {
        this.internalIdResource = internalIdResource;
    }

    public String getSspIdResource() {
        return this.sspIdResource;
    }

    public void setSspIdResource(String sspIdResource) {
        this.sspIdResource = sspIdResource;
    }



    public String getSspIdParent() {
        return this.sspIdParent;
    }

    public void setSspIdParent(String sspIdParent) {
        this.sspIdParent = sspIdParent;
    }

    public String getSymIdParent() {
        return this.symIdParent;
    }

    public void setSymIdParent(String symIdParent) {
        this.symIdParent = symIdParent;
    }

    public IAccessPolicySpecifier getAccessPolicy() {
        return this.accessPolicy;
    }

    public void setAccessPolicy(IAccessPolicySpecifier accessPolicySpecifier) {
        this.accessPolicy = accessPolicySpecifier;
    }

    public IAccessPolicySpecifier getFilteringPolicy() {
        return this.filteringPolicy;
    }

    public void setFilteringPolicy(IAccessPolicySpecifier filteringPolicySpecifier) {
        this.filteringPolicy = filteringPolicySpecifier;
    }

    @JsonIgnore
    public Resource getSemanticDescription() {
        return this.resource;
    }

    public void setSemanticDesciption(Resource resource) {
        this.resource = resource;
    }

}
