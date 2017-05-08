package eu.h2020.symbiote.enabler.messaging.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Observation;


/**
* @author PetarKrivic <petar.krivic@fer.hr>
*/
public class EnablerLogicDataAppearedMessage {

	@JsonProperty("taskId")
    private String taskId;
	
	@JsonProperty("timestamp")
	private String timestamp;
	
	@JsonProperty("data")
	private List<Observation> data;
	
	public EnablerLogicDataAppearedMessage(){
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<Observation> getObservations() {
		return data;
	}

	public void setObservations(List<Observation> observations) {
		this.data = observations;
	}
}
