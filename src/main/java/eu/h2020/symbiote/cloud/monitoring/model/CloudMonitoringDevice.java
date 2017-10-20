package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

public class CloudMonitoringDevice {

	//symbioteID
	private String id; 
	
	//unused in r4
	private int availability;
	//unused in r4
	private int load;
	//unused in r4
	private String timestamp;

	private Date timemetric;
	
	private CloudMonitoringMetrics[] metrics;
	
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

	public CloudMonitoringMetrics[] getMetrics() {
		return metrics;
	}

	public void setMetrics(CloudMonitoringMetrics[] metrics) {
		this.metrics = metrics;
	}

	public Date getTimemetric() {
		return timemetric;
	}

	public void setTimemetric(Date timemetric) {
		this.timemetric = timemetric;
	}



}
