package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.AbstractRequest;

import java.util.List;

/**
 * POJO describing a request for resources' Interworking Interface URLs.
 */
public class ResourceUrlsRequest extends AbstractRequest<List<String>> {

    public ResourceUrlsRequest(String token, List<String> body) {
        super(token, body);
    }

    public ResourceUrlsRequest() {
        // Needed for Jackson serialization
    }

    public List<String> getIdList() {
        return super.getBody();
    }

    public void setIdList(List<String> idList) {
        super.setBody(idList);
    }

}