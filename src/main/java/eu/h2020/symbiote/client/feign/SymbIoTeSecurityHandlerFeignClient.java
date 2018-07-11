package eu.h2020.symbiote.client.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.commons.SecurityConstants;
import eu.h2020.symbiote.security.commons.Token;
import eu.h2020.symbiote.security.commons.credentials.AuthorizationCredentials;
import eu.h2020.symbiote.security.commons.credentials.HomeCredentials;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.commons.exceptions.custom.ValidationException;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import eu.h2020.symbiote.security.helpers.MutualAuthenticationHelper;
import feign.Client;
import feign.Request;
import feign.Response;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A feign client which uses the client security handler
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeSecurityHandlerFeignClient implements Client {

    private final ISecurityHandler securityHandler;
    private final String homePlatformId;
    private final String username;
    private final String password;
    private final String clientId;
    private final String serviceComponentId;
    private final String servicePlatformId;
    private final boolean validateResponse;
    private final Client client;

    /**
     * @param securityHandler       configured for this component
     * @param homePlatformId        the home Platform Id
     * @param username              the username in the home platform
     * @param password              the password in the home platform
     * @param clientId              the client id
     * @param serviceComponentId    id of the component this client is used to communicate with ({@link ComponentIdentifiers})
     * @param servicePlatformId     id of the platform which the component belongs to ({@link SecurityConstants#CORE_AAM_INSTANCE_ID}
     *                              for Symbiote core components)
     * @param validateResponse      indicated if the component will be validated
     * @param client                used for business logic
     */
    public SymbIoTeSecurityHandlerFeignClient(ISecurityHandler securityHandler,
                                              String homePlatformId,
                                              String username,
                                              String password,
                                              String clientId,
                                              String serviceComponentId,
                                              String servicePlatformId,
                                              boolean validateResponse,
                                              Client client) {
        this.securityHandler = securityHandler;
        this.homePlatformId = homePlatformId;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.serviceComponentId = serviceComponentId;
        this.servicePlatformId = servicePlatformId;
        this.validateResponse = validateResponse;
        this.client = client;
    }


    @Override
    public Response execute(Request request, Request.Options options) throws IOException {

        String errMsg;

        try {

            Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();

            securityHandler.getCertificate(availableAAMs.get(homePlatformId), username, password, clientId);

            Token homeToken = securityHandler.login(availableAAMs.get(homePlatformId));

            HomeCredentials homeCredentials = securityHandler.getAcquiredCredentials().get(homePlatformId).homeCredentials;
            authorizationCredentialsSet.add(new AuthorizationCredentials(homeToken, homeCredentials.homeAAM, homeCredentials));

            SecurityRequest securityRequest = MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);


            Map<String, Collection<String>> headers = securityRequest.getSecurityRequestHeaderParams().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> Collections.singletonList(entry.getValue())));

            headers.putAll(request.headers());

            Request newRequest = Request.create(request.method(), request.url(),
                    headers, request.body(), request.charset());

            Response response = client.execute(newRequest, options);

            if (validateResponse && response.status() >= 200 && response.status() < 300) {
                String serviceResponse = response.headers().get(SecurityConstants.SECURITY_RESPONSE_HEADER)
                        .iterator().next();

                if (serviceResponse != null && !serviceResponse.isEmpty()) {

                    try {
                        if (!MutualAuthenticationHelper.isServiceResponseVerified(
                                serviceResponse, securityHandler.getComponentCertificate(serviceComponentId, servicePlatformId))) {
                            return Response.builder().status(400).reason("Server response verification failed")
                                    .body("Server response verification failed".getBytes())
                                    .headers(response.headers()).build();
                        }
                    } catch (SecurityHandlerException e) {
                        return Response.builder().status(400).reason("Server response verification failed: " + e.getErrorMessage())
                                .body(("Server response verification failed: "+ e.getErrorMessage()).getBytes())
                                .headers(response.headers()).build();
                    } catch (CertificateException e) {
                        return Response.builder().status(400).reason("Server response verification failed: " + e.getMessage())
                                .body(("Server response verification failed: "+ e.getMessage()).getBytes())
                                .headers(response.headers()).build();
                    }
                } else {
                    return Response.builder().status(400).reason("Missing server challenge response")
                            .body("Missing server challenge response".getBytes())
                            .headers(response.headers()).build();
                }
            }

            return response;

        } catch (SecurityHandlerException | ValidationException e) {
            errMsg = e.getErrorMessage();
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            errMsg = e.getMessage();
        }

        String msg = "Can't get authorization credentials";
        if (errMsg != null) {
            msg += ": " + errMsg;
        }
        return Response.builder().status(401).reason(msg)
                .body(msg.getBytes())
                .headers(new HashMap<>()).build();
    }
}