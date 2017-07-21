package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import org.springframework.data.annotation.Id;

import java.util.List;


public class ResourceManagerTaskInfoRequest {

    @Id
    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("coreQueryRequest")
    private CoreQueryRequest coreQueryRequest;

    @JsonProperty("interval")
    private Integer interval;

    @JsonProperty("allowCaching")
    private Boolean allowCaching;

    @JsonProperty("cachingInterval")
    private Long cachingInterval;

    @JsonProperty("informPlatformProxy")
    private Boolean informPlatformProxy;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    public ResourceManagerTaskInfoRequest() {
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }

    public CoreQueryRequest getCoreQueryRequest() { return coreQueryRequest; }
    public void setCoreQueryRequest(CoreQueryRequest coreQueryRequest) { this.coreQueryRequest = coreQueryRequest; }

    public Integer getInterval() { return interval; }
    public void setInterval(Integer interval) { this.interval = interval; }

    public Boolean getAllowCaching() { return allowCaching; }
    public void setAllowCaching(Boolean allowCaching) { this.allowCaching = allowCaching; }

    public Long getCachingInterval() { return  cachingInterval; }
    public void setCachingInterval(Long cachingInterval) { this.cachingInterval = cachingInterval; }

    public Boolean getInformPlatformProxy() { return  informPlatformProxy; }
    public void setInformPlatformProxy(Boolean informPlatformProxy) { this.informPlatformProxy = informPlatformProxy; }

    public String getEnablerLogicName() { return enablerLogicName; }
    public void setEnablerLogicName(String enablerLogicName) { this.enablerLogicName = enablerLogicName; }
}
