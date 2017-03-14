package eu.h2020.symbiote.messaging.core;

import feign.Headers;
import feign.RequestLine;

	
public interface CoreAAMRestService {
	@RequestLine("GET /rootCertificate")
	@Headers("Acceted: multipart/form-data")
    public byte[] getRootCertificate(); 
/*
    @RequestLine("GET /resource?resourceInternalId={resourceInternalId}")
    @Headers("Content-Type: application/json")
    public CloudResource getResource(@Param("resourceInternalId")  String resourceInternalId);

    @RequestLine("POST /resource")
    @Headers("Content-Type: application/json")
    public CloudResource addResource(CloudResource resource);

    @RequestLine("PUT /resource")
    @Headers("Content-Type: application/json")
    public CloudResource updateResource(CloudResource resource);

    @RequestLine("DELETE /resource?resourceInternalId={resourceInternalId}")
    @Headers("Content-Type: application/json")
    public CloudResource deleteResource(@Param("resourceInternalId")  String resourceInternalId); */
}
