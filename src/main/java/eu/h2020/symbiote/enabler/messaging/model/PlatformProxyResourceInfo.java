package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class PlatformProxyResourceInfo {

    @JsonProperty("resourceId")
    private String resourceId;

    @JsonProperty("accessURL")
    private String accessURL;


    public PlatformProxyResourceInfo() {
    }

    public String getResourceId() { return resourceId; }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAccessURL() {
        return accessURL;
    }

    public void setAccessURL(String accessURL) {
        this.accessURL = accessURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlatformProxyResourceInfo that = (PlatformProxyResourceInfo) o;

        if (resourceId != null ? !resourceId.equals(that.resourceId) : that.resourceId != null) return false;
        return accessURL != null ? accessURL.equals(that.accessURL) : that.accessURL == null;

    }

    @Override
    public int hashCode() {
        int result = resourceId != null ? resourceId.hashCode() : 0;
        result = 31 * result + (accessURL != null ? accessURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlatformProxyResourceInfo{" +
                "resourceId='" + resourceId + '\'' +
                ", accessURL='" + accessURL + '\'' +
                '}';
    }
}
