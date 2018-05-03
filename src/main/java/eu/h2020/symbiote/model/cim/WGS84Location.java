/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 * @author <a href="mailto:aleksandar.antonic@fer.hr">Aleksandar Antonic</a>
 */
@QueryEntity
@Document
public class WGS84Location extends Location {

    @JsonProperty("longitude")
    private final double longitude;
    @JsonProperty("latitude")
    private final double latitude;
    @JsonProperty("altitude")
    private final double altitude;

    @JsonCreator
    @PersistenceConstructor
    public WGS84Location(@JsonProperty("longitude") double longitude, 
                         @JsonProperty("latitude") double latitude, 
                         @JsonProperty("altitude") double altitude, 
                         @JsonProperty("name") String name,
                         @JsonProperty("description") List<String> description) {
        //super(label, comment);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.name = name;
        this.description = description;
    }

    public WGS84Location(WGS84Location l) {
    	super(l.name, l.description);
    	this.longitude=l.longitude;
    	this.latitude=l.latitude;
    	this.altitude=l.altitude;
	}

    
	public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getAltitude() {
        return altitude;
    }


    @Override
    public String toString() {
    	StringBuffer b=new StringBuffer();
    	b.append("A WGS84 location lo=").append(this.longitude);
    	b.append("/la=").append(this.latitude);
    	b.append("/al=").append(this.altitude);
    	b.append("\n");
    	b.append(super.toString());
    	return b.toString();
    }
    
    
    @Override
    public boolean equals(Object other) {
    	
    	if ( ! super.equals(other) )	// This call also includes the obligatory checks for identity, null and so on.
    		return false;
    	
    	if ( ! (other instanceof WGS84Location) )
    		return false;
    	
    	WGS84Location o=(WGS84Location)other;
    	
    	if (this.latitude!=o.latitude)
    		return false;
    	
    	if (this.longitude!=o.longitude)
    		return false;
    	
    	if (this.altitude!=o.altitude)
    		return false;
    	
    	return true;
    }
    
    /**
     * This class can be used as a hash index.
     */
    @Override
    public int hashCode() {
    	int result=super.hashCode();
    	result+=(latitude*1000); // *1000 to increase the chance that coordinates nearby have a different hashcode 
    	result+=(longitude*1000); 
    	result+=altitude;
    	return result;
    }
}
