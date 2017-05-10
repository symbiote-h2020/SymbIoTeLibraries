package eu.h2020.symbiote.security.rest;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.payloads.CheckTokenRevocationResponse;
import eu.h2020.symbiote.security.payloads.Credentials;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface AAMRestService {
    @RequestLine("GET " + SecurityHandlerConstants.GET_CORE_AAM_CA_CERTIFICATE)
    @Headers("Accept: multipart/form-data")
    String getRootCertificate();

    @RequestLine("POST " + SecurityHandlerConstants.DO_CORE_AAM_LOGIN)
    @Headers("Content-Type: application/json")
    Response login(Credentials credential);

    @RequestLine("POST " + SecurityHandlerConstants.DO_CORE_AAM_CHECK_TOKEN_REVOCATION)
    @Headers({"Content-Type: application/json", "Accept: application/json", AAMConstants.TOKEN_HEADER_NAME + ": " +
            "{token}"})
    CheckTokenRevocationResponse checkTokenRevocation(@Param("token") String token);

    @RequestLine("POST " + SecurityHandlerConstants.DO_REQUEST_FOREIGN_TOKEN)
    @Headers({"Content-Type: application/json", "Accept: application/json", AAMConstants.TOKEN_HEADER_NAME + ": " +
            "{token}"})
    Response requestCoreToken(@Param("token") String token);

    @RequestLine("POST " + SecurityHandlerConstants.DO_REQUEST_FOREIGN_TOKEN)
    @Headers({"Content-Type: application/json", "Accept: application/json", AAMConstants.TOKEN_HEADER_NAME + ": " +
            "{token}"})
    Response requestForeignToken(@Param("token") String token);
}

