/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.cci.accessNotificationMessages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 *
 * @author <a href="mailto:l.tomaselli@nextworks.it">Luca Tomaselli</a>
 */
public class FailedAccessMessageInfo extends MessageInfo {
    @JsonProperty("code")
    private String code;
    
    @JsonProperty("message")
    private String message;
    
    @JsonProperty("appId")
    private String appId;
    
    @JsonProperty("issuer")
    private String issuer;
    
    @JsonProperty("validationStatus")
    private String validationStatus;
    
    @JsonProperty("requestParams")
    private String requestParams;

    public FailedAccessMessageInfo() {
        // empty constructor
    }

    public FailedAccessMessageInfo(String symbIoTeId, List<Date> timestamps,
                                   String code, String message, String appId, String issuer,
                                   String validationStatus, String requestParams) {
        this.symbIoTeId = symbIoTeId;
        this.timestamps = timestamps;
        this.code = code;
        this.message = message;
        this.appId = appId;
        this.issuer = issuer;
        this.validationStatus = validationStatus;
        this.requestParams = requestParams;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(String ValidationStatus) {
        this.validationStatus = ValidationStatus;
    }

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams;
    }
    
    
}
