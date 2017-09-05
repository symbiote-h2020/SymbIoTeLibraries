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
public class Property {
    
    @JsonProperty("label")
    private final String label;
    @JsonProperty("comment")
    private final String comment;

    @JsonCreator
    public Property(@JsonProperty("label") String label, 
                    @JsonProperty("comment") String comment) {
        this.label = label;
        this.comment = comment;
    }

    public Property(Property p) {
    	this.label=p.label;
    	this.comment=p.comment;
	}

	public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }
 
    
    // Helper
    
    
    /**
     * This is a drop in replacement of a routine that is availabe on later java versions (Objects.areEqual).
     * It can be removed when the compiler version for this code is set to 1.8 or higher.
     * @param o1
     * @param o2
     * @return
     */
    boolean areEqual(Object o1, Object o2) {
    	if ( (o1==null) && (o2==null))
    		return true;
    	
    	if ((o1==null) && (o2!=null))
    		return false;
    	
    	return o1.equals(o2);
    }
    
    // See areEqual above
    int hashCodeFor(Object o1) {
    	if (o1==null)
    		return 0;
    	
    	return o1.hashCode();
    }
    

    
    @Override
    public String toString() {
    	StringBuffer buffer=new StringBuffer();
    	
    	buffer.append("Property:");
    	buffer.append("label=").append(label).append(",");
    	buffer.append("comment=").append(comment);
    	
    	return buffer.toString();
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o==null)
    		return false;
    	
    	if (this==o)
    		return true;
    	
    	if (!(o instanceof Property))
    		return false;
    	
    	Property op=(Property)o;
    	
    	if (!areEqual(this.label, op.label))
    		return false;
    	
//    	if (!areEqual(this.comment, op.comment))	// Commented out. Comments should not play a role in "equals".
//    		return false;
    	
    	return true;
    }
    
    @Override
    public int hashCode() {
    	return hashCodeFor(this.label);
    }
}

