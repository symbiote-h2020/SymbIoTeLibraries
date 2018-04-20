/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:aleksandar.antonic@fer.hr">Aleksandar Antonic</a>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WGS84Location.class, name = "WGS84Location"),
        @JsonSubTypes.Type(value = WKTLocation.class, name = "WKTLocation"),
        @JsonSubTypes.Type(value = SymbolicLocation.class, name = "SymbolicLocation")
})
@QueryEntity
@Document
abstract public class Location {
    @JsonProperty("name")
    String name;
    @JsonProperty("description")
    List<String> description;

    
    public Location() {	// Jackson needs an empty constructor (or a real complicated one :-) )
    }

    public Location(String n, List<String> d) {
    	this.name = n;
    	this.description = d;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
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
    	
    	b.append("Name is ").append(name).append("\n");
    	b.append("Description is ").append(description);
    	
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
    	
    	if (!Objects.equals(this.name, o.name))
    		return false;

//		Check for comments commented out. We have not forgotten this field but think it's not a criteria for equality.
//    	if (!Objects.equals(this.comments, o.comments))
//    		return false;

    	return true;
    }
    
    @Override
    public int hashCode() {
    	// Note: comments are not part of the hashCode on purpose. Also see "equals".
    	if (name==null)
    		return 42;
    	return name.hashCode();
    }
    
}