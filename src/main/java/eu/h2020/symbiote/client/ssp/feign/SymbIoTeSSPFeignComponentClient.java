package eu.h2020.symbiote.client.ssp.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.commons.SecurityConstants;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;
import eu.h2020.symbiote.security.handler.IComponentSecurityHandler;
import feign.Client;
import feign.Request;
import feign.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * A feign client which uses the client security handler
 *
 * @author Vasilis Glykantzis
 */
public class SymbIoTeSSPFeignComponentClient implements Client {
    private static final Log logger = LogFactory.getLog(SymbIoTeSSPFeignComponentClient.class);

    private final IComponentSecurityHandler securityHandler;
    private final String serviceComponentId;
    private final String serviceSSPId;
    private final Client client = new Default(null, null);


    /**
     * Constructor for creating a security-enabled client with HOME Token
     *
     * @param securityHandler       configured for this component
     * @param serviceComponentId    id of the component this client is used to communicate with ({@link ComponentIdentifiers})
     * @param serviceSSPId          id of the SSP which the component belongs to ({@link SecurityConstants#CORE_AAM_INSTANCE_ID}
     *                              for symbIoTe core components)
     */
    public SymbIoTeSSPFeignComponentClient(IComponentSecurityHandler securityHandler,
                                           String serviceComponentId,
                                           String serviceSSPId) {
        this.securityHandler = securityHandler;
        this.serviceComponentId = serviceComponentId;
        this.serviceSSPId = serviceSSPId;
    }

    /**
     * Constructor for creating a security-disabled client
     */
    public SymbIoTeSSPFeignComponentClient() {
        this(null, null, null);
    }


    @Override
    public Response execute(Request request, Request.Options options) throws IOException {

        String errMsg;

        Boolean serverValidation = false;

        try {

            if (securityHandler != null) {

                serverValidation = Boolean.valueOf(request.headers().get(SERVER_VALIDATION_HEADER).iterator().next());
                SecurityRequest securityRequest = securityHandler.generateSecurityRequestUsingLocalCredentials();


                // Insert it to the headers
                Map<String, Collection<String>> headers = securityRequest.getSecurityRequestHeaderParams().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> Collections.singletonList(entry.getValue())));

                // Add the headers from the Feign request
                headers.putAll(request.headers());

                // Remove the helper headers
                headers.remove(SERVER_VALIDATION_HEADER);

                logger.debug("HEADERS = " + headers);

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

                if (serviceResponse != null) {
                    try {
                        securityHandler.isReceivedServiceResponseVerified(serviceResponse, serviceComponentId, serviceSSPId);
                    } catch (SecurityHandlerException e) {
                        return Response.builder().status(400).reason("Server response verification failed: " + e.getErrorMessage())
                                .body(("Server response verification failed: "+ e.getErrorMessage()).getBytes())
                                .headers(response.headers()).build();
                    }
                } else {
                    return Response.builder().status(400).reason("Missing server challenge response")
                            .body("Missing server challenge response".getBytes())
                            .headers(response.headers()).build();
                }
            }

            return response;

        } catch (SecurityHandlerException e) {
            errMsg = e.getErrorMessage();
        } catch (JsonProcessingException e) {
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