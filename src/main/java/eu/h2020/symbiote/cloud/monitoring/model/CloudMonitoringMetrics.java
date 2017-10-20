package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

public class CloudMonitoringMetrics {

	private String tag; 
	private int value;
	private Date datemin;
	private Date datemax;
	private int valuemin;
	private int valuemax;
	
	
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getDatemin() {
		return datemin;
	}
	public void setDatemin(Date datemin) {
		this.datemin = datemin;
	}
	public Date getDatemax() {
		return datemax;
	}
	public void setDatemax(Date datemax) {
		this.datemax = datemax;
	}
	public int getValuemin() {
		return valuemin;
	}
	public void setValuemin(int valuemin) {
		this.valuemin = valuemin;
	}
	public int getValuemax() {
		return valuemax;
	}
	public void setValuemax(int valuemax) {
		this.valuemax = valuemax;
	}
	
	
}
