package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AggregatedMetrics {
  
  String deviceId;
  String tag;
  
  List<TimedValue> values = new ArrayList<>();
  
  Map<String, Double> statistics = new HashMap<>();
  
  Map<String, Integer> counts = new HashMap<>();
  
  public String getDeviceId() {
    return deviceId;
  }
  
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  
  public String getTag() {
    return tag;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
  public List<TimedValue> getValues() {
    return values;
  }
  
  public void setValues(List<TimedValue> values) {
    this.values = values;
  }
  
  public Map<String, Double> getStatistics() {
    return statistics;
  }
  
  public void setStatistics(Map<String, Double> statistics) {
    this.statistics = statistics;
  }
  
  public Map<String, Integer> getCounts() {
    return counts;
  }
  
  public void setCounts(Map<String, Integer> counts) {
    this.counts = counts;
  }
}
