package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;

import java.util.Date;

public class ResourceSharingInformation {

    private Date sharingDate;
    private Boolean bartering;

    @JsonProperty("accessPolicy")
    private IAccessPolicySpecifier accessPolicy;
    @JsonProperty("filteringPolicy")
    private IAccessPolicySpecifier filteringPolicy;

    public Date getSharingDate() {
        return sharingDate;
    }

    public void setSharingDate(Date sharingDate) {
        this.sharingDate = sharingDate;
    }

    public Boolean getBartering() {
        return bartering;
    }

    public void setBartering(Boolean bartering) {
        this.bartering = bartering;
    }

    public IAccessPolicySpecifier getAccessPolicy() {
        return accessPolicy;
    }

    public void setAccessPolicy(IAccessPolicySpecifier accessPolicy) {
        this.accessPolicy = accessPolicy;
    }

    public IAccessPolicySpecifier getFilteringPolicy() {
        return filteringPolicy;
    }

    public void setFilteringPolicy(IAccessPolicySpecifier filteringPolicy) {
        this.filteringPolicy = filteringPolicy;
    }
}
