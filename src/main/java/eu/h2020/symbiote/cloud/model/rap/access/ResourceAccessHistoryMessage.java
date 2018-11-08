/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.access;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.h2020.symbiote.cloud.model.rap.ResourceInfo;
import eu.h2020.symbiote.cloud.model.rap.query.Query;

import java.util.List;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class ResourceAccessHistoryMessage extends ResourceAccessMessage {
    
    @JsonProperty("resourceInfo")
    List<ResourceInfo> resInfo;
    
    @JsonProperty("top")
    int top;
    
    @JsonProperty("filter")
    private final Query filter;
    /**
     * JSON Constructor
     * @param resInfo               the resource data information   
     * @param top                   the size of the requested collection
     * @param filter                the filter for getResourceUrls
     */
    
    
    @JsonCreator
    public ResourceAccessHistoryMessage(@JsonProperty("resourceInfo") List<ResourceInfo> resInfo,
            @JsonProperty("top") int top,
            @JsonProperty("filter") Query filter){
        this.accessType = AccessType.HISTORY;
        this.resInfo = resInfo;
        this.top = top;
        this.filter = filter;
    }
    
    public int getTop() {
        return top;
    }
    
    public Query getFilter(){
        return this.filter;
    }
 
    @JsonProperty("resourceInfo")
    public List<ResourceInfo> getResourceInfo(){
        return this.resInfo;
    }
}
