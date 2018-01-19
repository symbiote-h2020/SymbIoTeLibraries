/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.cci.accessNotificationMessages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author <a href="mailto:l.tomaselli@nextworks.it">Luca Tomaselli</a>
 */
public class SuccessfulAccessMessageInfo extends MessageInfo {
    public enum AccessType {
        NORMAL,SUBSCRIPTION_START,SUBSCRIPTION_END
    }
    
    @JsonProperty("accessType")
    private String accessType;

    public SuccessfulAccessMessageInfo() {
        this.timestamps = new ArrayList<>();
    }

    public SuccessfulAccessMessageInfo(String symbIoTeId, List<Date> timestamps, String accessType) {
        this.symbIoTeId = symbIoTeId;
        this.timestamps = timestamps;
        this.accessType = accessType;
    }

    public String getAccessType() {
        return accessType;
    }
    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
    
}
