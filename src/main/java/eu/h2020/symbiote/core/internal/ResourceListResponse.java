package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.List;

/**
 * Created by mateuszl on 25.07.2017.
 */
public class ResourceListResponse {
    private int status;
    private String message;
    private List<Resource> resources;

    public ResourceListResponse() {
    }

    public ResourceListResponse(int status, String message, List<Resource> resources) {
        this.status = status;
        this.message = message;
        this.resources = resources;
    }

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
