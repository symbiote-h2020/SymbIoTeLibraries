package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class ProblematicResourcesMessage {

    @JsonProperty("problematicResourcesInfoList")
    private List<ProblematicResourcesInfo> problematicResourcesInfoList;

    public ProblematicResourcesMessage() {
        problematicResourcesInfoList = new ArrayList<>();
    }

    public List<ProblematicResourcesInfo> getProblematicResourcesInfoList() {
        return problematicResourcesInfoList;
    }
    public void setProblematicResourcesInfoList(List<ProblematicResourcesInfo> problematicResourcesInfoList) {
        this.problematicResourcesInfoList = problematicResourcesInfoList;


    }
}
