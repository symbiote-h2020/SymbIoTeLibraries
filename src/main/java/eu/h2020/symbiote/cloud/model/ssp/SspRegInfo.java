package eu.h2020.symbiote.cloud.model.ssp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


public class SspRegInfo {
    @org.springframework.data.annotation.Id
    @JsonProperty("symId") 		//of  SDEV
    private String symId;
    @JsonProperty("sspId")  //of  SDEV
    private String sspId;
    @JsonProperty("pluginId")
    private String pluginId;
    @JsonProperty("pluginURL") // Interworking Interface URL
    private String pluginURL;
    @JsonProperty("dk1")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String derivedKey1;
    @JsonProperty("hashField")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hashField;

    @JsonProperty("roaming")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean roaming;


    public SspRegInfo() {
    }

    public String getSspId() {
        return this.sspId;
    }

    public void setSspId(String sspId) {
        this.sspId = sspId;
    }

    public String getSymId() {
        return this.symId;
    }

    public void setSymId(String symId) {
        this.symId = symId;
    }

    public String getPluginId() {
        return this.pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public boolean getRoaming() {
        return this.roaming;
    }

    public void setRoaming(boolean roaming) {
        this.roaming = roaming;
    }


    public String getPluginURL() { return pluginURL; }

    public void setPluginUrl(String pluginUrl) { this.pluginURL = pluginURL; }

    public String getDerivedKey1() { return derivedKey1; }

    public void setDerivedKey1(String derivedKey1) { this.derivedKey1 = derivedKey1; }

    public String getHashField() { return hashField; }

    public void setHashField(String hashField) { this.hashField = hashField; }


}
