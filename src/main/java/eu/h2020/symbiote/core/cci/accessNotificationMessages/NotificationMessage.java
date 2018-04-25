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
public class NotificationMessage {
    @JsonProperty("successfulAttempts")
    private List<SuccessfulAccessMessageInfo> successfulAttempts;
    
    @JsonProperty("successfulPushes")
    private List<SuccessfulPushesMessageInfo> successfulPushes;
    
    @JsonProperty("failedAttempts")
    private List<FailedAccessMessageInfo> failedAttempts;

    public NotificationMessage() {
        successfulAttempts = new ArrayList<>();
        successfulPushes = new ArrayList<>();
        failedAttempts = new ArrayList<>();
    }

    public List<SuccessfulAccessMessageInfo> getSuccessfulAttempts() { return successfulAttempts; }
    public void setSuccessfulAttempts(List<SuccessfulAccessMessageInfo> successfulAttempts) {
        this.successfulAttempts = successfulAttempts;
    }

    public List<SuccessfulPushesMessageInfo> getSuccessfulPushes() { return successfulPushes; }
    public void setSuccessfulPushes(List<SuccessfulPushesMessageInfo> successfulPushes) {
        this.successfulPushes = successfulPushes;
    }

    public List<FailedAccessMessageInfo> getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(List<FailedAccessMessageInfo> failedAttempts) {
        this.failedAttempts = failedAttempts;
    }
            
    public void setSuccessfulAttempts (String symbioTeId, List<Date> timestamps, String accessType){
        SuccessfulAccessMessageInfo succAccMess = new SuccessfulAccessMessageInfo(symbioTeId, timestamps, accessType);
        this.successfulAttempts = new ArrayList<>();
        this.successfulAttempts.add(succAccMess);
    }
    
    public void setSuccessfulAttemptsList (List<String> symbioTeIdList, List<Date> timestamps, String accessType){
        this.successfulAttempts = new ArrayList<>();
        for(String symbioteId: symbioTeIdList){
            SuccessfulAccessMessageInfo succAccMess = new SuccessfulAccessMessageInfo(symbioteId, timestamps, accessType);
            this.successfulAttempts.add(succAccMess);
        }
    }
    
    public void setSuccessfulPushes (String symbioTeId, List<Date> timestamps){
        SuccessfulPushesMessageInfo succPushMess = new SuccessfulPushesMessageInfo(symbioTeId, timestamps);
        this.successfulPushes = new ArrayList<>();
        this.successfulPushes.add(succPushMess);
    }
    
    public void setFailedAttempts (String symbioTeId, List<Date> timestamps,
            String code, String message, String appId, String issuer, 
            String validationStatus, String requestParams) {
        FailedAccessMessageInfo failMess= new FailedAccessMessageInfo(symbioTeId, timestamps,
                code, message, appId, issuer, validationStatus, requestParams);
        this.failedAttempts = new ArrayList<>();
        this.failedAttempts.add(failMess);
    }

    public void addSuccessfulAttempt(SuccessfulAccessMessageInfo messageInfo) {
        if (successfulAttempts == null)
            successfulAttempts = new ArrayList<>();
        successfulAttempts.add(messageInfo);
    }

    public void addSuccessfulAttempts(List<SuccessfulAccessMessageInfo> messageInfoList) {
        if (successfulAttempts == null)
            successfulAttempts = new ArrayList<>();
        successfulAttempts.addAll(messageInfoList);
    }

    public void addSuccessfulAttempts (String symbioTeId, List<Date> timestamp, String accessType){
        SuccessfulAccessMessageInfo succAccMess = new SuccessfulAccessMessageInfo(symbioTeId, timestamp, accessType);
        this.successfulAttempts.add(succAccMess);
    }

    public void addSuccessfulAttemptsList (List<String> symbioTeIdList, List<Date> timestamp, String accessType){
        for(String symbioteId: symbioTeIdList){
            SuccessfulAccessMessageInfo succAccMess = new SuccessfulAccessMessageInfo(symbioteId, timestamp, accessType);
            this.successfulAttempts.add(succAccMess);
        }
    }

    public void addSuccessfulPush(SuccessfulPushesMessageInfo messageInfo) {
        if (successfulPushes == null)
            successfulPushes = new ArrayList<>();
        successfulPushes.add(messageInfo);
    }

    public void addSuccessfulPushes(List<SuccessfulPushesMessageInfo> messageInfoList) {
        if (successfulPushes == null)
            successfulPushes = new ArrayList<>();
        successfulPushes.addAll(messageInfoList);
    }

    public void addSuccessfulPushes (String symbioTeId, List<Date> timestamp){
        SuccessfulPushesMessageInfo succPushMess = new SuccessfulPushesMessageInfo(symbioTeId, timestamp);
        this.successfulPushes.add(succPushMess);
    }

    public void addFailedAttempt(FailedAccessMessageInfo messageInfo) {
        if (failedAttempts == null)
            failedAttempts = new ArrayList<>();
        failedAttempts.add(messageInfo);
    }

    public void addFailedAttempts(List<FailedAccessMessageInfo> messageInfoList) {
        if (failedAttempts == null)
            failedAttempts = new ArrayList<>();
        failedAttempts.addAll(messageInfoList);
    }

    public void addFailedAttempts (String symbioTeId, List<Date> timestamp,
                                   String code, String message, String appId, String issuer,
                                   String validationStatus, String requestParams) {
        FailedAccessMessageInfo failMess= new FailedAccessMessageInfo(symbioTeId, timestamp,
                code, message, appId, issuer, validationStatus, requestParams);
        this.failedAttempts.add(failMess);
    }


    public void clear() {
        successfulAttempts.clear();
        successfulPushes.clear();
        failedAttempts.clear();
    }

    public static void SendSuccessfulAttemptsMessage(String message){
        
    }
    
    public static void SendFailAccessMessage(String message){
        
    }
    
    public static void SendSuccessfulPushMessage(String message){
        
    }
}
