package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.Platform;

/**
 * Created by mateuszl on 07.08.2017.
 */
public class PlatformRegistryResponse {

    private int status;
    private String message;
    private Platform platform;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public PlatformRegistryResponse() {
        //Empty constructor
    }
}
