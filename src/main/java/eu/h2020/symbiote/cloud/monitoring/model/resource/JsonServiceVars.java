package eu.h2020.symbiote.cloud.monitoring.model.resource;

public class JsonServiceVars {

	private double attempt;
	
	private boolean reachable;
	
	private double state;
	
	private double state_type;
	
	
	public JsonServiceVars(){
		
	}


	public double getAttempt() {
		return attempt;
	}


	public void setAttempt(double attempt) {
		this.attempt = attempt;
	}


	public boolean isReachable() {
		return reachable;
	}


	public void setReachable(boolean reachable) {
		this.reachable = reachable;
	}


	public double getState() {
		return state;
	}


	public void setState(double state) {
		this.state = state;
	}


	public double getState_type() {
		return state_type;
	}


	public void setState_type(double state_type) {
		this.state_type = state_type;
	}
	
	
}
