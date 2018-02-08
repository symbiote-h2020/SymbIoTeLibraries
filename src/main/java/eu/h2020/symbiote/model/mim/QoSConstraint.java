package eu.h2020.symbiote.model.mim;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class QoSConstraint {
  
  @JsonProperty("metric")
  @NotNull
  private QoSMetric metric;
  
  @JsonProperty("comparator")
  @NotNull
  private Comparator comparator;
  
  @JsonProperty("threshold")
  @NotNull
  @Valid
  private Double threshold;
  
  @JsonProperty("duration")
  @Valid
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
  
  public Double getThreshold() {
    return threshold;
  }
  public void setThreshold(Double threshold) {
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
