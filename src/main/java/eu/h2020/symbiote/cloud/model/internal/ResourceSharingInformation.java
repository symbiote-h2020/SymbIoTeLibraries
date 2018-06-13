package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ResourceSharingInformation {

    @JsonProperty("symbioteId")
    private String symbioteId;

    @JsonProperty("sharingDate")
    private Date sharingDate;

    @JsonProperty("bartering")
    private Boolean bartering;

    public String getSymbioteId() { return symbioteId; }
    public void setSymbioteId(String symbioteId) { this.symbioteId = symbioteId; }

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
