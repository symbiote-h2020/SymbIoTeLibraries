package eu.h2020.symbiote.messaging.restAAM;

import eu.h2020.symbiote.constants.SHConstants;
import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import feign.Headers;
import feign.RequestLine;

	
public interface AAMRestService {
	@RequestLine("GET "+SHConstants.GET_CORE_AAM_CA_CERTIFICATE)
	@Headers("Acceted: multipart/form-data")
    public byte[] getRootCertificate();
	
	@RequestLine("POST "+SHConstants.DO_CORE_AAM_LOGIN)
	@Headers("Content-Type: application/json")
    public Token login(Credential credential); 

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
