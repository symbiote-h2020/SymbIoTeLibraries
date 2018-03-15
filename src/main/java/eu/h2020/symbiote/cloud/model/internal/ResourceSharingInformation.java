package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;

import java.util.Date;

public class ResourceSharingInformation {

    private Date sharingDate;
    private Boolean bartering;

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

}
