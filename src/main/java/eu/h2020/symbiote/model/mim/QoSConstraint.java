package eu.h2020.symbiote.model.mim;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QoSConstraint {
  
  @JsonProperty("metric")
  private QoSMetric metric;
  
  @JsonProperty("comparator")
  private Comparator comparator;
  
  @JsonProperty("threshold")
  private double threshold;
  
  @JsonProperty("duration")
  private Integer duration;
  
  @JsonProperty("resourceType")
  private String resourceType;
  
  public QoSMetric getMetric() {
    return metric;
  }
  
  public void setMetric(QoSMetric metric) {
    this.metric = metric;
  }
  
  public Comparator getComparator() {
    return comparator;
  }
  
  public void setComparator(Comparator comparator) {
    this.comparator = comparator;
  }
  
  public double getThreshold() {
    return threshold;
  }
  
  public void setThreshold(double threshold) {
    this.threshold = threshold;
  }
  
  public Integer getDuration() {
    return duration;
  }
  
  public void setDuration(Integer duration) {
    this.duration = duration;
  }
  
  public String getResourceType() {
    return resourceType;
  }
  
  public void setResourceType(String resourceType) {
    this.resourceType = resourceType;
  }
}
