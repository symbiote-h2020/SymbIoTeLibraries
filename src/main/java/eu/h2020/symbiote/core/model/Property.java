/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.data;

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

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }
    
}
