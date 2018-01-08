package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Objects;

public class DeviceMetric extends Metric {
  
  
  private String deviceId;
  
  public String getDeviceId() {
    return deviceId;
  }
  
  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeviceMetric that = (DeviceMetric) o;
    return Objects.equals(getDeviceId(), that.getDeviceId()) &&
               Objects.equals(getTag(), that.getTag()) &&
               Objects.equals(getDate(), that.getDate()) &&
               Objects.equals(getValue(), that.getValue());
  }
  
}
