/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.data.observation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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
    private final String resultTime;
    @JsonProperty("samplingTime")
    private final String samplingTime;
    @JsonProperty("obsValues")
    private final List<ObservationValue> obsValues;

    @JsonCreator
    public Observation(@JsonProperty("resourceId")String resourceId, 
                       @JsonProperty("location")Location location, 
                       @JsonProperty("resultTime")String resultTime, 
                       @JsonProperty("samplingTime")String samplingTime, 
                       @JsonProperty("obsValues")List<ObservationValue> obsValues) {
        this.resourceId = resourceId;
        this.location = location;
        this.resultTime = resultTime;
        this.samplingTime = samplingTime;
        this.obsValues = obsValues;
    }

    public String getResourceId() {
        return resourceId;
    }

    public Location getLocation() {
        return location;
    }

    public String getResultTime() {
        return resultTime;
    }

    public String getSamplingTime() {
        return samplingTime;
    }

    public List<ObservationValue> getObsValues() {
        return obsValues;
    }
    
    
}
