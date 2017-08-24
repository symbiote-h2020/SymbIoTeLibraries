/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class WGS84Location extends Location {
    
    @JsonProperty("longitude")
    private final double longitude;
    @JsonProperty("latitude")
    private final double latitude;
    @JsonProperty("altitude")
    private final double altitude;

    @JsonCreator
    public WGS84Location(@JsonProperty("longitude") double longitude, 
                         @JsonProperty("latitude") double latitude, 
                         @JsonProperty("altitude") double altitude, 
                         @JsonProperty("name") List<String> labels,
                         @JsonProperty("description") List<String> comments) {
        //super(label, comment);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.labels = labels;
        this.comments = comments;
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
    
}
