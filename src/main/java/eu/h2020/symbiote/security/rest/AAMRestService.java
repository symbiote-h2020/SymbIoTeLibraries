package eu.h2020.symbiote.security.rest;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface AAMRestService {
    @RequestLine("GET " + SecurityHandlerConstants.GET_CORE_AAM_CA_CERTIFICATE)
    @Headers("Accept: multipart/form-data")
    String getRootCertificate();

    @RequestLine("POST " + SecurityHandlerConstants.DO_CORE_AAM_LOGIN)
    @Headers("Content-Type: application/json")
    Token login(Credentials credential);

    @RequestLine("POST " + SecurityHandlerConstants.DO_CORE_AAM_CHECK_TOKEN_REVOCATION)
    @Headers({"Content-Type: application/json", "Accept: application/json", AAMConstants.TOKEN_HEADER_NAME + ": " +
            "{token}"})
    TokenValidationStatus checkTokenRevocation(@Param("token") String token);

    @RequestLine("POST " + SecurityHandlerConstants.DO_REQUEST_CORE_TOKEN)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Token requestCoreToken(Token token);

    @RequestLine("POST " + SecurityHandlerConstants.DO_REQUEST_FOREIGN_TOKEN)
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Token requestForeignToken(Token token);

}

