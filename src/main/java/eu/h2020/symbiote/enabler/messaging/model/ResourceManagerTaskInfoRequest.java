package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.util.IntervalFormatter;

import org.springframework.data.annotation.Id;

import java.util.Objects;


public class ResourceManagerTaskInfoRequest {

    public static final int ALL_AVAILABLE_RESOURCES = -1;

    @Id
    @JsonProperty("taskId")
    private String taskId;

    @JsonProperty("minNoResources")
    private Integer minNoResources;

    @JsonProperty("maxNoResources")
    private Integer maxNoResources = ALL_AVAILABLE_RESOURCES;

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

    @JsonProperty("sparqlQueryRequest")
    private SparqlQueryRequest sparqlQueryRequest;


    public ResourceManagerTaskInfoRequest() {
        // empty constructor
    }

    /**
     *
     * @param taskId                the id of the requested task
     * @param minNoResources        the minimum number of required resources. The maximum number of resources is configured
     *                              by setting the maxNoResources, which defaults to ALL_AVAILABLE_RESOURCES
     * @param coreQueryRequest      the request which is propagated to the core
     * @param queryInterval         the query interval in ISO-8601 alternateExtended format that is propagated to the
     *                              Platform Proxy
     * @param allowCaching          if the results gotten from search are allowed to be cached for faster responses
     *                              in case of failing resources
     * @param cachingInterval       the caching interval of tasks resources in ISO-8601 alternateExtended format
     * @param informPlatformProxy   if Platform Proxy needs to be informed. If you want to receive back data set to true.
     *                              Otherwise, if you just need to query the Core for getting back the resource
     *                              descriptions, set to false
     * @param enablerLogicName      the enabler logic component which owns this task and it will receive updates for it
     * @param sparqlQueryRequest    the request in SPARQL. Set to null if you use CoreQueryRequest. If set overwrites
     *                              the CoreQueryRequest
     * @throws IllegalArgumentException if queryInterval/cachingInterval has wrong format or both sparqlQueryRequest and coreQueryRequest are null
     * @see                         <a href="http://joda-time.sourceforge.net/apidocs/org/joda/time/format/ISOPeriodFormat.html#alternateExtended()">ISO-8601 alternateExtended format</a>
     */
    public ResourceManagerTaskInfoRequest(String taskId, Integer minNoResources,
                                          CoreQueryRequest coreQueryRequest, String queryInterval,
                                          Boolean allowCaching, String cachingInterval,
                                          Boolean informPlatformProxy, String enablerLogicName,
                                          SparqlQueryRequest sparqlQueryRequest)
            throws IllegalArgumentException {

        if (sparqlQueryRequest == null && coreQueryRequest == null)
            throw new IllegalArgumentException("Both sparqlQueryRequest and coreQueryRequest are null");

        setTaskId(taskId);
        setMinNoResources(minNoResources);
        setCoreQueryRequest(CoreQueryRequest.newInstance(coreQueryRequest));
        setQueryInterval(queryInterval);
        setAllowCaching(allowCaching);
        setCachingInterval(cachingInterval);
        setInformPlatformProxy(informPlatformProxy);
        setEnablerLogicName(enablerLogicName);

        if (sparqlQueryRequest != null)
            setSparqlQueryRequest(new SparqlQueryRequest(sparqlQueryRequest));
        else
            setSparqlQueryRequest(null);
    }

    public ResourceManagerTaskInfoRequest(ResourceManagerTaskInfoRequest resourceManagerTaskInfoRequest) {
        setTaskId(resourceManagerTaskInfoRequest.getTaskId());
        setMinNoResources(resourceManagerTaskInfoRequest.getMinNoResources());
        setMaxNoResources(resourceManagerTaskInfoRequest.getMaxNoResources());

        if (resourceManagerTaskInfoRequest.getCoreQueryRequest() != null)
            setCoreQueryRequest(CoreQueryRequest.newInstance(resourceManagerTaskInfoRequest.getCoreQueryRequest()));
        else
            setCoreQueryRequest(null);

        setQueryInterval(resourceManagerTaskInfoRequest.getQueryInterval());
        setAllowCaching(resourceManagerTaskInfoRequest.getAllowCaching());
        setCachingInterval(resourceManagerTaskInfoRequest.getCachingInterval());
        setInformPlatformProxy(resourceManagerTaskInfoRequest.getInformPlatformProxy());
        setEnablerLogicName(resourceManagerTaskInfoRequest.getEnablerLogicName());

        if (resourceManagerTaskInfoRequest.getSparqlQueryRequest() != null)
            setSparqlQueryRequest((new SparqlQueryRequest(resourceManagerTaskInfoRequest.getSparqlQueryRequest())));
        else
            setSparqlQueryRequest(null);
    }

    public String getTaskId() { return taskId; }
    public void setTaskId(String taskId) { this.taskId = taskId; }

    public Integer getMinNoResources() { return minNoResources; }
    public void setMinNoResources(Integer minNoResources) {
        if (this.maxNoResources != ALL_AVAILABLE_RESOURCES && this.maxNoResources < minNoResources)
            throw new IllegalArgumentException("minNoResources should not be greater than maxNoResources");
        this.minNoResources = minNoResources;
    }

    public Integer getMaxNoResources() { return maxNoResources; }
    public void setMaxNoResources(Integer maxNoResources) throws  IllegalArgumentException {
        if (maxNoResources != ALL_AVAILABLE_RESOURCES && maxNoResources < this.minNoResources)
            throw new IllegalArgumentException("minNoResources should not be greater than maxNoResources");
        this.maxNoResources = maxNoResources;
    }

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

    public SparqlQueryRequest getSparqlQueryRequest() { return sparqlQueryRequest; }
    public void setSparqlQueryRequest(SparqlQueryRequest sparqlQueryRequest) { this.sparqlQueryRequest = sparqlQueryRequest; }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;

        // null check
        if (o == null)
            return false;

        // type check and cast
        if (!(o instanceof ResourceManagerTaskInfoRequest))
            return false;

        ResourceManagerTaskInfoRequest request = (ResourceManagerTaskInfoRequest) o;
        // field comparison
        return Objects.equals(this.getTaskId(), request.getTaskId())
                && Objects.equals(this.getMinNoResources(), request.getMinNoResources())
                && Objects.equals(this.getMaxNoResources(), request.getMaxNoResources())
                && Objects.equals(this.getCoreQueryRequest(), request.getCoreQueryRequest())
                && Objects.equals(this.getQueryInterval(), request.getQueryInterval())
                && Objects.equals(this.getAllowCaching(), request.getAllowCaching())
                && Objects.equals(this.getCachingInterval(), request.getCachingInterval())
                && Objects.equals(this.getInformPlatformProxy(), request.getInformPlatformProxy())
                && Objects.equals(this.getEnablerLogicName(), request.getEnablerLogicName())
                && Objects.equals(this.getSparqlQueryRequest(), request.getSparqlQueryRequest());
    }

	@Override
	public String toString() {
		return "ResourceManagerTaskInfoRequest [taskId=" + taskId + ", minNoResources=" + minNoResources
				+ ", maxNoResources=" + maxNoResources + ", coreQueryRequest=" + coreQueryRequest + ", queryInterval="
				+ queryInterval + ", allowCaching=" + allowCaching + ", cachingInterval=" + cachingInterval
				+ ", informPlatformProxy=" + informPlatformProxy + ", enablerLogicName=" + enablerLogicName
				+ ", sparqlQueryRequest=" + sparqlQueryRequest + "]";
	}
}
