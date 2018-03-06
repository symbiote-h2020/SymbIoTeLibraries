/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.mim;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author <a href="mailto:michael.jacoby@iosb.fraunhofer.de">Michael Jacoby</a>
 */
public class FederationMember {

    @JsonProperty("platformId")
    @Pattern(regexp="^[\\w-]{4,}$")
    @Size(max=30)
    private String platformId;

    @JsonProperty("interworkingServiceURL")
    private String interworkingServiceURL;

    public FederationMember() {
    }

    public FederationMember(String platformId, String interworkingServiceURL) {
        this.platformId = platformId;
        this.interworkingServiceURL = interworkingServiceURL;
    }

    public String getPlatformId() {
        return this.platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getInterworkingServiceURL() {
        return this.interworkingServiceURL;
    }

    public void setInterworkingServiceURL(String interworkingServiceURL) {
        this.interworkingServiceURL = interworkingServiceURL;
    }
}
