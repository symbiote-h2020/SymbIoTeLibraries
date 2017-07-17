package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;


public class ResourceManagerTaskInfoRequest {

    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("location")
    private String location;   

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    @JsonProperty("interval")
    private Integer interval;

    @JsonProperty("allowCaching")
    private Boolean allowCaching;

    @JsonProperty("cachingInterval")
    private Long cachingInterval;

    @JsonProperty("informPlatformProxy")
    private Boolean informPlatformProxy;


    public ResourceManagerTaskInfoRequest() {
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<String> getObservesProperty() { return observesProperty; }
    public void setObservesProperty(List<String> observesProperty) { this.observesProperty = observesProperty; }

    public Integer getInterval() { return interval; }
    public void setInterval(Integer interval) { this.interval = interval; }

    public Boolean getAllowCaching() { return allowCaching; }
    public void setAllowCaching(Boolean allowCaching) { this.allowCaching = allowCaching; }

    public Long getCachingInterval() { return  cachingInterval; }
    public void setCachingInterval(Long cachingInterval) { this.cachingInterval = cachingInterval; }

    public Boolean getInformPlatformProxy() { return  informPlatformProxy; }
    public void setInformPlatformProxy(Boolean informPlatformProxy) { this.informPlatformProxy = informPlatformProxy; }

}
