package eu.h2020.symbiote.cloud.model.internal;

import java.util.Date;

public class ResourceSharingInformation {
    private String symbioteId;
    private Date sharingDate;
    private Boolean bartering;

    public String getSymbioteId() {
        return symbioteId;
    }

    public void setSymbioteId(String symbioteId) {
        this.symbioteId = symbioteId;
    }

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
