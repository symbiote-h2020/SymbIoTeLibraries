package eu.h2020.symbiote.core.model;

import org.springframework.data.annotation.Id;


public class Location {

    @Id
    private String id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private double altitude;

    public Location() {

    }
    
    public Location(double longitude, double latitude, 
                    double altitude, String name, String description) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}