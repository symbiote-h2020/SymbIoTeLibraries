package eu.h2020.symbiote.core.internal;

import java.util.List;

/**
 * POJO describing a request for resources' Interworking Interface URLs.
 */
public class ResourceUrlsRequest {
    private List<String> idList;

    /**
     * Default empty constructor.
     */
    public ResourceUrlsRequest() {
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }
}