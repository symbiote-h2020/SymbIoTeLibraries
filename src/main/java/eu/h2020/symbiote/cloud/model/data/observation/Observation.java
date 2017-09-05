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
    
    // Helper
    
    /**
     * This is a drop in replacement of a routine that is availabe on later java versions (Objects.areEqual).
     * It can be removed when the compiler version for this code is set to 1.8 or higher.
     * @param o1
     * @param o2
     * @return
     */
    private static boolean areEqual(Object o1, Object o2) {
    	if ( (o1==null) && (o2==null))
    		return true;
    	
    	if ((o1==null) && (o2!=null))
    		return false;
    	
    	return o1.equals(o2);
    }
    
    // See areEqual above
    private static int hashCodeFor(Object o1) {
    	if (o1==null)
    		return 0;
    	
    	return o1.hashCode();
    }
    

    @Override
    public String toString() {
    	StringBuffer buffer=new StringBuffer();
    	
    	buffer.append("Observation by ").append(this.resourceId).append("@").append(this.location).append("\n");
    	buffer.append("Made at ").append(samplingTime).append("for ").append(resultTime).append("\n");
    	
    	buffer.append("ObservationValues:");
    	if (this.obsValues==null) {
    		buffer.append("null");
    	} else {
	    	buffer.append(this.obsValues.subList(0, 3)).append("\n");
	    	if (this.obsValues.size()>3)
	    		buffer.append("... (").append(this.obsValues.size()-3).append("more values\n");
    	}
    	
    	return buffer.toString();
    }

    @Override
    public boolean equals(Object o) {
    	
    	if (o==null)
    		return false;
    	
    	if (this==o)
    		return true;
    	
    	if (!(o instanceof Observation))
    		return false;
    	
    	Observation obs=(Observation)o;
    	
    	
    	if (!areEqual(this.resourceId, obs.resourceId))
    		return false;
    	
    	if (!areEqual(this.resultTime, obs.resultTime))
    		return false;
    	
    	if (!areEqual(this.samplingTime, obs.samplingTime))
    		return false;
    	
    	if (!areEqual(this.location, obs.location))
    		return false;
    	
    	if (!areEqual(this.obsValues, obs.obsValues))
    		return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int result=42;
    	
    	result+=hashCodeFor(this.resourceId)*3;
    	result+=hashCodeFor(this.resultTime)*5;
    	result+=hashCodeFor(this.samplingTime)*7;
    	result+=hashCodeFor(this.location)*11;
    	result+=hashCodeFor(this.obsValues)*13;
    	
    	return result;
    }
}
