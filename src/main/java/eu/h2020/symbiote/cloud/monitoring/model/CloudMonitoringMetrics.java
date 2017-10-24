package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

/**
 *  
 * This class contains the details of the metrics, the generic format (tag, value) is used to save the metrics. 
 * In case the metric is of aggregated data additional attributes are used to details such as date range and range of values
 * 
 * @author: Fernando Campos
 * @version: 20/10/2017
 * 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringPlatform 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringDevice
 */
public class CloudMonitoringMetrics {
    
	/**
     * Contains types of metrics or kpis, according to the following established way
     * tag="simpletext", this represents a simple data value. 
     * 
     * tag="simpletext.number", this represents an accumulated data value, where: 
     * 		simpletext is the generic metric name
	 *		number is the period in days before the event time.
     */
	private String tag; 
	/**
	 * Contains the simple or accumulated data value.
	 */
	private int value;
	/**
	 * In case inform accumulated data value, is the "date from" of the query range
	 */
	private Date datemin;
	/**
	 * In case inform accumulated data value, is the "date to" of the query range
	 */
	private Date datemax;
	/**
	 * In case inform accumulated data value, is the minimum value of the query range 
	 */
	private int valuemin;
	/**
	 * In case inform accumulated data value, is the maximum value of the query range 
	 */
	private int valuemax;
	/**
	 * In case inform accumulated data value, is the count value of the query range 
	 */
	private int count;	
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
