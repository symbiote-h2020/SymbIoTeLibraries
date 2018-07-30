package eu.h2020.symbiote.ssp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

public class InnkeeperRegistrationResponse {
    private static Log log = LogFactory.getLog(InnkeeperRegistrationResponse.class);


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("symId")
    private String symId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("sspId")
    private String sspId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private String result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("registrationExpiration")
    private Integer registrationExpiration;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("updatedSymId")
    private List<Map<String,String>> updatedSymId;

    public InnkeeperRegistrationResponse() {
    }

    public InnkeeperRegistrationResponse(String symId, String sspId, String result) {
        this.symId=symId;
        this.sspId=sspId;
        this.result=result;
    }
    public InnkeeperRegistrationResponse(String symId, String sspId, String result, Integer registration_expiration) {
        this.symId=symId;
        this.sspId=sspId;
        this.result=result;
        this.registrationExpiration=registration_expiration;
    }
    public String getSymId() {
        return this.symId;
    }

    public void setSymId(String symId) {
        this.symId=symId;
    }
    public String getSspId() {
        return this.sspId;
    }
    public String getResult() {
        return this.result;
    }
    public void setResult(String result) {
        this.result=result;
    }

    public Integer getRegistrationExpiration() {
        return this.registrationExpiration;
    }

    public List<Map<String,String>> getupdatedSymId(){
        return this.updatedSymId;
    }


    public void setUpdatedSymId( List<Map<String,String>> updatedSymId) {
        this.updatedSymId=updatedSymId;
    }

    public void setSspId(String sspId) {
        this.sspId=sspId;

    }

}
