package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import org.springframework.data.annotation.Id;

import java.util.List;


public class ResourceManagerTaskInfoRequest {

    @Id
    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("minNoResources")
    private Integer minNoResources;

    @JsonProperty("coreQueryRequest")
    private CoreQueryRequest coreQueryRequest;

    // Subject to change to more human friendly format
    @JsonProperty("queryInterval_ms")
    private Integer queryInterval_ms;

    @JsonProperty("allowCaching")
    private Boolean allowCaching;

    // Subject to change to more human friendly format
    @JsonProperty("cachingInterval_ms")
    private Long cachingInterval_ms;

    @JsonProperty("informPlatformProxy")
    private Boolean informPlatformProxy;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    public ResourceManagerTaskInfoRequest() {
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getMinNoResources() { return minNoResources; }
    public void setMinNoResources(Integer minNoResources) { this.minNoResources = minNoResources; }

    public CoreQueryRequest getCoreQueryRequest() { return coreQueryRequest; }
    public void setCoreQueryRequest(CoreQueryRequest coreQueryRequest) { this.coreQueryRequest = coreQueryRequest; }

    public Integer getQueryInterval_ms() { return queryInterval_ms; }
    public void setQueryInterval_ms(Integer queryInterval_ms) { this.queryInterval_ms = queryInterval_ms; }

    public Boolean getAllowCaching() { return allowCaching; }
    public void setAllowCaching(Boolean allowCaching) { this.allowCaching = allowCaching; }

    public Long getCachingInterval_ms() { return  cachingInterval_ms; }
    public void setCachingInterval_ms(Long cachingInterval_ms) { this.cachingInterval_ms = cachingInterval_ms; }

    public Boolean getInformPlatformProxy() { return  informPlatformProxy; }
    public void setInformPlatformProxy(Boolean informPlatformProxy) { this.informPlatformProxy = informPlatformProxy; }

    public String getEnablerLogicName() { return enablerLogicName; }
    public void setEnablerLogicName(String enablerLogicName) { this.enablerLogicName = enablerLogicName; }
}
