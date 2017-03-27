/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonSubTypes({
        @JsonSubTypes.Type(value = WGS84Location.class,   name = "WGS84Location")
})
abstract public class AbstractLocation {
    @JsonProperty("name")
    String label;
    @JsonProperty("description")
    String comment;

    /*
    public Location(String label, String comment) {
        this.label = label;
        this.comment = comment;
    }*/

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }
}