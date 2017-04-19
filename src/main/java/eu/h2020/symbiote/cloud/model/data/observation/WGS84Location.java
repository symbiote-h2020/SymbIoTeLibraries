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
public class WGS84Location extends AbstractLocation {
    
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
                         @JsonProperty("name") String name, 
                         @JsonProperty("description") String comment) {
        //super(label, comment);
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.name = name;
        this.comment = comment;
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