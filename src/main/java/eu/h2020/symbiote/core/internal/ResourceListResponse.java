package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.resources.Resource;

import java.util.List;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * Created by mateuszl on 25.07.2017.
 */
public class ResourceListResponse extends StatusResponse<List<Resource>>{

    public ResourceListResponse() {
    }

    public ResourceListResponse(int status, String message, List<Resource> body) {
        super(status, message, body);
    }

    public List<Resource> getResources() {
        return super.getBody();
    }

    public void setResources(List<Resource> resources) {
        super.setBody(resources);
    }
}
