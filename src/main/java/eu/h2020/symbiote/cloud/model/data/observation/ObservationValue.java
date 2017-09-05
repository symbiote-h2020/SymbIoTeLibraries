/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.data.observation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class ObservationValue {
    @JsonProperty("value")
    private final String value;
    @JsonProperty("obsProperty")
    private final Property obsProperty;
    @JsonProperty("uom")
    private final UnitOfMeasurement uom;

    @JsonCreator
    public ObservationValue(@JsonProperty("value") String value, 
                            @JsonProperty("obsProperty") Property obsProperty, 
                            @JsonProperty("uom") UnitOfMeasurement uom) {
        this.value = value;
        this.obsProperty = obsProperty;
        this.uom = uom;
    }

    public String getValue() {
        return value;
    }

    public Property getObsProperty() {
        return obsProperty;
    }

    public UnitOfMeasurement getUom() {
        return uom;
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
    	
    	buffer.append("ObservationValue:");
    	buffer.append("value=").append(value).append(",");
    	buffer.append("uom=").append(uom).append(",");
    	buffer.append("obsProp=").append(obsProperty);

    	return buffer.toString();
    }
    
    @Override
    public boolean equals(Object o) {
    	
    	if (o==null)
    		return false;
    	
    	if (this==o)
    		return true;
    	
    	if (!(o instanceof ObservationValue))
    		return false;
    	
    	ObservationValue ov=(ObservationValue)o;
    	
    	if (!areEqual(this.value, ov.value))
    		return false;
    	
    	if (!areEqual(this.uom, ov.uom))
    		return false;
    	
    	if (!areEqual(this.obsProperty, ov.obsProperty))
    		return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	int result=42;
    	result+=3*hashCodeFor(this.value);
    	result+=5*hashCodeFor(this.uom);
    	result+=5*hashCodeFor(this.obsProperty);
    	
    	return result;
    }
}
