package eu.h2020.symbiote.cloud.monitoring.model;

public class CloudMonitoringDevice {

	//symbioteID
	private String id; 
	
	private int availability;
	
	private int load;
	
	private String timestamp;
	
	public CloudMonitoringDevice(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
