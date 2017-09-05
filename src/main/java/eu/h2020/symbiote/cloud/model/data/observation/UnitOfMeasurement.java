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
public class UnitOfMeasurement {
    
    @JsonProperty("symbol")
    private final String symbol;
    @JsonProperty("label")
    private final String label;
    @JsonProperty("comment")
    private final String comment;

    @JsonCreator
    public UnitOfMeasurement(@JsonProperty("symbol") String symbol, 
                             @JsonProperty("label") String label, 
                             @JsonProperty("comment") String comment) {
        this.symbol = symbol;
        this.label = label;
        this.comment = comment;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    // Helper
    @Override
    public String toString() {
    	return symbol==null ? "null" : symbol.toString();
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o==null)
    		return false;
    	
    	if (this==o)
    		return true;
    	
    	if (!(o instanceof UnitOfMeasurement))
    		return false;
    	
    	UnitOfMeasurement ou=(UnitOfMeasurement)o;
    	
    	if (this.symbol==null && ou.symbol==null)
    		return true;
    	
    	if (this.symbol==null)	// We already know, the other is not null
    		return false;
    	
    	return this.symbol.equals(ou.symbol);
    }
    
    @Override
    public int hashCode() {
    	if (this.symbol==null)
    		return 0;
    	
    	return this.symbol.hashCode();
    }
}
