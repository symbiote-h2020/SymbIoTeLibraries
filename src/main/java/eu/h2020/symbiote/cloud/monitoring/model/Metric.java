package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

public class Metric {
  
  private String tag;
  private String value;
  private Date date;
  
  public String getTag() {
    return tag;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
  public String getValue() {
    return value;
  }
  
  public void setValue(String value) {
    this.value = value;
  }
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
}
