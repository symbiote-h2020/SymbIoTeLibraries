package eu.h2020.symbiote.cloud.monitoring.model;

public class Metric extends TimedValue {
  
  private String tag;
  
  public String getTag() {
    return tag;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
}
