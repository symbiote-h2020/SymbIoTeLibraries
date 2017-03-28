package eu.h2020.symbiote.cloud.monitoring.model;

import eu.h2020.symbiote.cloud.monitoring.model.resource.JsonServiceLastCheckResult;

public class CloudMonitoringResource {

private boolean active;
	
	private String check_command;
	
	private double check_interval;
	
	private String display_name;
	
	private String last_check;
	
	private JsonServiceLastCheckResult last_check_result;
	
	public CloudMonitoringResource(){
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCheck_command() {
		return check_command;
	}

	public void setCheck_command(String check_command) {
		this.check_command = check_command;
	}

	public double getCheck_interval() {
		return check_interval;
	}

	public void setCheck_interval(double check_interval) {
		this.check_interval = check_interval;
	}

	public String getLast_check() {
		return last_check;
	}

	public void setLast_check(String last_check) {
		this.last_check = last_check;
	}

	public JsonServiceLastCheckResult getLast_check_result() {
		return last_check_result;
	}

	public void setLast_check_result(JsonServiceLastCheckResult last_check_result) {
		this.last_check_result = last_check_result;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
}
