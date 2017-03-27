/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class Observation {
    
    @JsonProperty("resourceId")
    private final String resourceId;
    @JsonProperty("location")
    private final WGS84Location location;
    @JsonProperty("resultTime")
    private final long resultTime;
    @JsonProperty("samplingTime")
    private final long samplingTime;
    @JsonProperty("obsValue")
    private final ObservationValue obsValue;    

    @JsonCreator
    public Observation(@JsonProperty("resourceId")String resourceId, 
                       @JsonProperty("location")WGS84Location location, 
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
    
    
    public static Observation observationExampleValue () {
        Logger log = LoggerFactory.getLogger(Observation.class);
        
        String sensorId = "symbIoTeID1";
        WGS84Location loc = new WGS84Location(15.9, 45.8, 145, "Spansko", "City of Zagreb");
        long timestamp = System.currentTimeMillis();
        ObservationValue obsval = new ObservationValue((double)7, new Property("Temperature", "Air temperature"), new UnitOfMeasurement("C", "degree Celsius", ""));
        Observation obs = new Observation(sensorId, loc, timestamp, timestamp-1000 , obsval);
        
        log.debug("Observation: \n" + obs.toString());
        
        return obs;
    }
}
