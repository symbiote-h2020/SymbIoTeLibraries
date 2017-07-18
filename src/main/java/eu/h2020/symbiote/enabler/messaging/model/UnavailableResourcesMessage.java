package eu.h2020.symbiote.enabler.messaging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class UnavailableResourcesMessage {
    private List<UnavailableResourcesInfo> unavailableResourcesInfoList;

    public UnavailableResourcesMessage() {
        unavailableResourcesInfoList = new ArrayList<>();
    }

    public List<UnavailableResourcesInfo> getUnavailableResourcesInfoList() {
        return unavailableResourcesInfoList;
    }
    public void setUnavailableResourcesInfoList(List<UnavailableResourcesInfo> unavailableResourcesInfoList) {
        this.unavailableResourcesInfoList = unavailableResourcesInfoList;
    }
}
