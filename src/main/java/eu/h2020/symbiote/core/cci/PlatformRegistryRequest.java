package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.Platform;

/**
 * Created by mateuszl on 07.08.2017.
 */
public class PlatformRegistryRequest {

    private String token;
    private Platform platform;

    public PlatformRegistryRequest() {
        //Empty constructor
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
