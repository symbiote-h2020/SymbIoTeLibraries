/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
    String label;
    @JsonProperty("description")
    String comment;


    public String getLabel() {
        return label;
    }
    
    public String getComment() {
        return comment;
    }
}