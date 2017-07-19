package eu.h2020.symbiote.enabler.messaging.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 7/18/2017.
 */
public class ProblematicResourcesMessage {
    private List<ProblematicResourcesInfo> problematicResourcesInfoList;

    public ProblematicResourcesMessage() {
        problematicResourcesInfoList = new ArrayList<>();
    }

    public List<ProblematicResourcesInfo> getUnavailableResourcesInfoList() {
        return problematicResourcesInfoList;
    }
    public void setUnavailableResourcesInfoList(List<ProblematicResourcesInfo> problematicResourcesInfoList) {
        this.problematicResourcesInfoList = problematicResourcesInfoList;
    }
}
