package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

public class TimedValue {
  
  private Date date;
  private String value;
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public String getValue() {
    return value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
}
