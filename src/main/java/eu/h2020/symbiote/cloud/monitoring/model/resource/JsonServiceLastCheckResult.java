package eu.h2020.symbiote.cloud.monitoring.model.resource;

public class JsonServiceLastCheckResult {

	private boolean active;
	
	private String check_source;
	
	private String[] command;
	
	private String execution_end;
	
	private String execution_start;
	
	private double exit_status;
	
	private String output;
	
	private String schedule_end;
	
	private String schedule_start;
	
	private double state;
	
	private String type;
	
	private JsonServiceVars vars_after;
	
	private JsonServiceVars vars_before;
	
	public JsonServiceLastCheckResult(){
		
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCheck_source() {
		return check_source;
	}

	public void setCheck_source(String check_source) {
		this.check_source = check_source;
	}

	public String[] getCommand() {
		return command;
	}

	public void setCommand(String[] command) {
		this.command = command;
	}

	public String getExecution_end() {
		return execution_end;
	}

	public void setExecution_end(String execution_end) {
		this.execution_end = execution_end;
	}

	public String getExecution_start() {
		return execution_start;
	}

	public void setExecution_start(String execution_start) {
		this.execution_start = execution_start;
	}

	public double getExit_status() {
		return exit_status;
	}

	public void setExit_status(double exit_status) {
		this.exit_status = exit_status;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getSchedule_end() {
		return schedule_end;
	}

	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}

	public String getSchedule_start() {
		return schedule_start;
	}

	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}

	public double getState() {
		return state;
	}

	public void setState(double state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonServiceVars getVars_after() {
		return vars_after;
	}

	public void setVars_after(JsonServiceVars vars_after) {
		this.vars_after = vars_after;
	}

	public JsonServiceVars getVars_before() {
		return vars_before;
	}

	public void setVars_before(JsonServiceVars vars_before) {
		this.vars_before = vars_before;
	}
	
}
