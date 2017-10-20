/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;
import java.util.Objects;

/**
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WGS84Location.class, name = "WGS84Location"),
        @JsonSubTypes.Type(value = WKTLocation.class, name = "WKTLocation"),
        @JsonSubTypes.Type(value = SymbolicLocation.class, name = "SymbolicLocation")
})
abstract public class Location {
    @JsonProperty("name")
    List<String> labels;
    @JsonProperty("description")
    List<String> comments;

    
    public Location() {	// Jackson needs an empty constructor (or a real complicated one :-) )
    }

    public Location(List<String> l, List<String> c) {
    	this.labels=l;
    	this.comments=c;
    }

    public List<String> getLabels() {
        return labels;
    }
    
    public List<String> getComments() {
        return comments;
    }

    
    public static Location makeCopy(Location l) {
		Location result=null;

    	if (l instanceof WGS84Location) {
    		result=new WGS84Location((WGS84Location)l);
    	}

    	if (l instanceof WKTLocation) {
    		result=new WKTLocation((WKTLocation)l);
    	}
    	
    	if (l instanceof SymbolicLocation) {
    		result=new SymbolicLocation((SymbolicLocation)l);
    	}
    	
    	return result;
    };
    
    
    @Override
    public String toString() {
    	StringBuffer b=new StringBuffer();
    	
    	b.append("labels are ").append(labels).append("\n");
    	b.append("Comments are ").append(comments);
    	
    	return b.toString();
    }
    
    @Override
    public boolean equals(Object other) {
    	
    	if (this==other)
    		return true;
    	
    	if (other==null)
    		return false;
    	
    	if ( ! (other instanceof Location) )
    		return false;

    	Location o=(Location)other;
    	
    	if (!Objects.equals(this.labels, o.labels))
    		return false;

//		Check for comments commented out. We have not forgotten this field but think it's not a criteria for equality.
//    	if (!Objects.equals(this.comments, o.comments))
//    		return false;

    	return true;
    }
    
    @Override
    public int hashCode() {
    	// Note: comments are not part of the hashCode on purpose. Also see "equals".
    	if (labels==null)
    		return 42;
    	return labels.hashCode();
    }
    
}