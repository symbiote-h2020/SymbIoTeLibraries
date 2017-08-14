package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.util.IntervalFormatter;
import org.springframework.data.annotation.Id;


public class ResourceManagerTaskInfoRequest {

    @Id
    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("minNoResources")
    private Integer minNoResources;

    @JsonProperty("coreQueryRequest")
    private CoreQueryRequest coreQueryRequest;

    // Use ISO-8601 alternateExtended format to specify the queryInterval below (i.e. Pyyyy-mm-ddThh:mm:ss)
    // Fractional seconds (milliseconds) are supported
    // http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISOPeriodFormat.html#alternateExtended()
    @JsonProperty("queryInterval")
    private String queryInterval;

    @JsonProperty("allowCaching")
    private Boolean allowCaching;

    // Use ISO-8601 alternateExtended format to specify the cachingInterval below (i.e. Pyyyy-mm-ddThh:mm:ss)
    // Fractional seconds (milliseconds) are supported
    // http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISOPeriodFormat.html#alternateExtended()
    @JsonProperty("cachingInterval")
    private String cachingInterval;

    @JsonProperty("informPlatformProxy")
    private Boolean informPlatformProxy;

    @JsonProperty("enablerLogicName")
    private String enablerLogicName;

    public ResourceManagerTaskInfoRequest() {
    }

    public ResourceManagerTaskInfoRequest(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        setTaskId(resourceManagerTaskInfoRequest.getTaskId());
        setMinNoResources(resourceManagerTaskInfoRequest.getMinNoResources());

        if (resourceManagerTaskInfoRequest.getCoreQueryRequest() != null)
            setCoreQueryRequest(CoreQueryRequest.newInstance(resourceManagerTaskInfoRequest.getCoreQueryRequest()));
        else
            setCoreQueryRequest(null);

        setQueryInterval(resourceManagerTaskInfoRequest.getQueryInterval());
        setAllowCaching(resourceManagerTaskInfoRequest.getAllowCaching());
        setCachingInterval(resourceManagerTaskInfoRequest.getCachingInterval());
        setInformPlatformProxy(resourceManagerTaskInfoRequest.getInformPlatformProxy());
        setEnablerLogicName(resourceManagerTaskInfoRequest.getEnablerLogicName());
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getMinNoResources() { return minNoResources; }
    public void setMinNoResources(Integer minNoResources) { this.minNoResources = minNoResources; }

    public CoreQueryRequest getCoreQueryRequest() { return coreQueryRequest; }
    public void setCoreQueryRequest(CoreQueryRequest coreQueryRequest) { this.coreQueryRequest = coreQueryRequest; }

    public String getQueryInterval() { return queryInterval; }
    public void setQueryInterval(String queryInterval) throws IllegalArgumentException {
        if (queryInterval != null) {
            IntervalFormatter interval = new IntervalFormatter(queryInterval);
        }
        this.queryInterval = queryInterval;
    }

    public Boolean getAllowCaching() { return allowCaching; }
    public void setAllowCaching(Boolean allowCaching) { this.allowCaching = allowCaching; }

    public String getCachingInterval() { return  cachingInterval; }
    public void setCachingInterval(String cachingInterval) throws IllegalArgumentException {
        if (cachingInterval != null) {
            IntervalFormatter interval = new IntervalFormatter(cachingInterval);
        }
        this.cachingInterval = cachingInterval;
    }

    public Boolean getInformPlatformProxy() { return  informPlatformProxy; }
    public void setInformPlatformProxy(Boolean informPlatformProxy) { this.informPlatformProxy = informPlatformProxy; }

    public String getEnablerLogicName() { return enablerLogicName; }
    public void setEnablerLogicName(String enablerLogicName) { this.enablerLogicName = enablerLogicName; }
}
