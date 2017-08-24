package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vasgl on 8/24/2017.
 */
public class ListResourcesResponse {

    @JsonProperty("resources")
    private List<InnkeeperListResourceInfo> innkeeperListResourceInfoList;

    public ListResourcesResponse() {
        // empty constructor
    }

    public List<InnkeeperListResourceInfo> getInnkeeperListResourceInfoList() {
        return innkeeperListResourceInfoList;
    }

    public void setInnkeeperListResourceInfoList(List<InnkeeperListResourceInfo> innkeeperListResourceInfoList) {
        this.innkeeperListResourceInfoList = innkeeperListResourceInfoList;
    }
}
