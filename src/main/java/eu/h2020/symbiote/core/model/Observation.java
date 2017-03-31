/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class Observation {
    
    @JsonProperty("resourceId")
    private final String resourceId;
    @JsonProperty("location")
    private final Location location;
    @JsonProperty("resultTime")
    private final long resultTime;
    @JsonProperty("samplingTime")
    private final long samplingTime;
    @JsonProperty("obsValue")
    private final ObservationValue obsValue;    

    @JsonCreator
    public Observation(@JsonProperty("resourceId")String resourceId, 
                       @JsonProperty("location")Location location, 
                       @JsonProperty("resultTime")long resultTime, 
                       @JsonProperty("samplingTime")long samplingTime, 
                       @JsonProperty("obsValue")ObservationValue obsValue) {
        this.resourceId = resourceId;
        this.location = location;
        this.resultTime = resultTime;
        this.samplingTime = samplingTime;
        this.obsValue = obsValue;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Location getLocation() {
        return location;
    }

    public long getResultTime() {
        return resultTime;
    }

    public long getSamplingTime() {
        return samplingTime;
    }

    public ObservationValue getObsValue() {
        return obsValue;
    }
    
    
}
