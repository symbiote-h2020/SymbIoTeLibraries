package eu.h2020.symbiote.client;


import eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringPlatform;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface CRMRestService {
	
	@RequestLine("POST " + ClientConstants.PUBLISH_MONITORING_DATA)
	@Headers("Content-Type: application/json")
    public void doPost2Crm(
    		@Param("platformId") String platformId, 
    		CloudMonitoringPlatform platform);


}

