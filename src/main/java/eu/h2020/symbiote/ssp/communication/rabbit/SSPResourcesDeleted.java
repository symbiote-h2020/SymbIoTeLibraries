package eu.h2020.symbiote.ssp.communication.rabbit;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vasgl on 8/30/2017.
 */
public class SSPResourcesDeleted {

    @JsonProperty("idList")
    private List<String> idList;

    public SSPResourcesDeleted() {
    }

    public SSPResourcesDeleted(List<String> idList) {
        setIdList(idList);
    }

    public List<String> getIdList() { return idList; }
    public void setIdList(List<String> idList) { this.idList = idList; }
}
