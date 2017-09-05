package eu.h2020.symbiote.cloud.model.data.observation;

import org.assertj.core.util.Objects;
import org.springframework.data.annotation.Id;


public class Location extends AbstractLocation {

    @Id
    private String id;
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

    public Location(Location loc) {
    	this.id=loc.id;
    	this.name=loc.name;
    	this.description=loc.description;
    	this.comment=loc.comment;
    	
    	this.latitude=loc.latitude;
    	this.longitude=loc.longitude;
    	this.altitude=loc.altitude;
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
    
    // Some little helper
    @Override
    public String toString() {
    	StringBuffer buffer=new StringBuffer();
    	buffer.append("Location: id=").append(id).append(",");
    	buffer.append("name=").append(name).append(",");
    	buffer.append("lon/lat/alt=").append(longitude).append("/").append(latitude).append("/").append(this.altitude).append(",");
    	buffer.append("desc.=").append(this.description);

    	return buffer.toString();
    }

    @Override
    public boolean equals(Object other) {
    	if (other==null)
    		return false;
    	
    	if (this==other)
    		return true;
    	
    	if (! (other instanceof Location) )
    		return false;
    	
    	Location ol=(Location)other;
    	
    	if (!Objects.areEqual(this.id, ol.id))
    		return false;
    	
    	if (!Objects.areEqual(this.name, ol.name))
    		return false;
    	
    	if (this.latitude!=ol.latitude)
    		return false;
    	
    	if (this.longitude!=ol.longitude)
    		return false;
    	
    	if (this.altitude!=ol.altitude)
    		return false;
    	
    	
    	// The next two compares are commented out to make clear I haven't forgotten them 
    	// but I think that they are not relevant for equality of two locations. 
//    	if (!Objects.areEqual(this.comment, ol.comment))
//    		return false;
    	
//    	if (!Objects.areEqual(this.description, ol.description))
//    		return false;
    
    	
    	return true;
    }
    
    @Override
	public int hashCode() {
    	int result=42;	// We have to start somewhere, don't we. And can you imagine some better suited then THE ANSWER? :-)
    	result+=Objects.hashCodeFor(this.id)*3;
    	result+=Objects.hashCodeFor(this.name)*5;
    	result+=7*(int)(this.longitude*1000);	// *1000 to reduce the risk of to locations nearby being rounded to the same int. 
    	result+=11*(int)(this.latitude*1000);
    	result+=13*(int)this.altitude;
    	return result;
    }

     
}