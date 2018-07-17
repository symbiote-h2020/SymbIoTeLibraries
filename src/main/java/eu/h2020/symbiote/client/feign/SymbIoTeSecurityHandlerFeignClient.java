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

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.HOME_PLATFORM_IDS_HEADER;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * A feign client which uses the client security handler
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeSecurityHandlerFeignClient implements Client {

    private final ISecurityHandler securityHandler;
    private final String serviceComponentId;
    private final String servicePlatformId;
    private final Client client = new Client.Default(null, null);


    /**
     * Constructor for creating a security-enabled client with HOME Token
     *
     * @param securityHandler       configured for this component
     * @param serviceComponentId    id of the component this client is used to communicate with ({@link ComponentIdentifiers})
     * @param servicePlatformId     id of the platform which the component belongs to ({@link SecurityConstants#CORE_AAM_INSTANCE_ID}
     *                              for symbIoTe core components)
     */
    public SymbIoTeSecurityHandlerFeignClient(ISecurityHandler securityHandler,
                                              String serviceComponentId,
                                              String servicePlatformId) {
        this.securityHandler = securityHandler;
        this.serviceComponentId = serviceComponentId;
        this.servicePlatformId = servicePlatformId;
    }

    /**
     * Constructor for creating a security-disabled client
     */
    public SymbIoTeSecurityHandlerFeignClient() {
        this(null, null, null);
    }


    @Override
    public Response execute(Request request, Request.Options options) throws IOException {

        String errMsg;

        Boolean serverValidation = false;

        try {

            if (securityHandler != null) {
                String homePlatformIdsAsString = request.headers().get(HOME_PLATFORM_IDS_HEADER).iterator().next();
                homePlatformIdsAsString = homePlatformIdsAsString.substring(1, homePlatformIdsAsString.length() - 1);
                Set<String> homePlatformIds = homePlatformIdsAsString.isEmpty() ?
                        new HashSet<>() :
                        new HashSet<>(Arrays.asList(homePlatformIdsAsString.split(", ")));
                serverValidation = Boolean.valueOf(request.headers().get(SERVER_VALIDATION_HEADER).iterator().next());

                SecurityRequest securityRequest;

                // Get the available AAMs from the Core AAM
                Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();

                if (homePlatformIds.isEmpty()) {
                    // Operate with GUEST Token from Core AAM

                    // Acquiring GUEST token
                    Token guestToken = securityHandler.loginAsGuest(availableAAMs.get(SecurityConstants.CORE_AAM_INSTANCE_ID));

                    // Create Security Request with GUEST Token
                    securityRequest = new SecurityRequest(guestToken.getToken());

                } else {

                    // Todo: Check the exceptions which are thrown here
                    // Operate with HOME Tokens
                    Set<AuthorizationCredentials> authorizationCredentialsSet = new HashSet<>();

                    for (String homePlatformId : homePlatformIds) {
                        // Acquiring HOME token
                        Token homeToken = securityHandler.login(availableAAMs.get(homePlatformId));

                        // Acquiring HOME credentials
                        HomeCredentials homeCredentials = securityHandler.getAcquiredCredentials().get(homePlatformId).homeCredentials;

                        // Populate the authorization credentials. From now on we do not need the password
                        authorizationCredentialsSet.add(new AuthorizationCredentials(homeToken, homeCredentials.homeAAM, homeCredentials));
                    }

                    // Create Security Request with HOME Token
                    securityRequest = MutualAuthenticationHelper.getSecurityRequest(authorizationCredentialsSet, false);
                }


                // Insert it to the headers
                Map<String, Collection<String>> headers = securityRequest.getSecurityRequestHeaderParams().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> Collections.singletonList(entry.getValue())));

                // Add the headers from the Feign request
                headers.putAll(request.headers());

                // Remove the helper headers
                headers.remove(HOME_PLATFORM_IDS_HEADER);
                headers.remove(SERVER_VALIDATION_HEADER);

                // Create the new Feign request
                request = Request.create(request.method(), request.url(),
                        headers, request.body(), request.charset());
            }

            // Execute the business query
            Response response = client.execute(request, options);

            // Optionally, validate the service response on success
            if (securityHandler != null && serverValidation &&
                    response.status() >= 200 && response.status() < 300) {
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