/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

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
    
}
