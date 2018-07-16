package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.model.cim.Observation;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * symbIoTe RAP client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignRAPClient implements RAPClient {

    private static final Log logger = LogFactory.getLog(FeignRAPClient.class);

    private ISecurityHandler securityHandler;
    private String homePlatformId;
    private String username;
    private String password;
    private String clientId;


    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param homePlatformId    the home Platform Id
     * @param username          the username in the home platform
     * @param password          the password in the home platform
     * @param clientId          the client id
     */
    public FeignRAPClient(ISecurityHandler securityHandler,
                          String homePlatformId,
                          String username,
                          String password,
                          String clientId) {

        this.securityHandler = securityHandler;
        this.homePlatformId = homePlatformId;
        this.username = username;
        this.password = password;
        this.clientId = clientId;
    }

    @Override
    public Observation getLatestObservation(String resourceUrl, boolean serverValidation) {
        try {
            return getClient(resourceUrl, serverValidation).getTopObservations(1).get(0);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get latest Observation", e);
            return null;
        }
    }

    @Override
    public List<Observation> getTopObservations(String resourceUrl, int top, boolean serverValidation) {
        try {
            return getClient(resourceUrl, serverValidation).getTopObservations(top);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get Observations", e);
            return null;
        }
    }

    @Override
    public void actuate(String resourceUrl, String body, boolean serverValidation) {
        try {
            getClient(resourceUrl, serverValidation).actuate(body);
        } catch (SecurityHandlerException e) {
            logger.error("Could not send actuation request", e);
        }
    }

    @Override
    public String invokeService(String resourceUrl, String body, boolean serverValidation) {
        try {
            return getClient(resourceUrl, serverValidation).invokeService(body);
        } catch (SecurityHandlerException e) {
            logger.error("Could not invoke service", e);
            return "Could not invoke service : " + e.getMessage();
        }
    }

    @Override
    public Observation getLatestObservationAsGuest(String resourceUrl, boolean serverValidation) {
        try {
            return getGuestClient(resourceUrl, serverValidation).getTopObservations(1).get(0);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get latest Observation", e);
            return null;
        }
    }

    @Override
    public List<Observation> getTopObservationsAsGuest(String resourceUrl, int top, boolean serverValidation) {
        try {
            return getGuestClient(resourceUrl, serverValidation).getTopObservations(top);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get Observations", e);
            return null;
        }
    }

    @Override
    public void actuateAsGuest(String resourceUrl, String body, boolean serverValidation) {
        try {
            getGuestClient(resourceUrl, serverValidation).actuate(body);
        } catch (SecurityHandlerException e) {
            logger.error("Could not send actuation request", e);
        }
    }

    @Override
    public String invokeServiceAsGuest(String resourceUrl, String body, boolean serverValidation) {
        try {
            return getGuestClient(resourceUrl, serverValidation).invokeService(body);
        } catch (SecurityHandlerException e) {
            logger.error("Could not invoke service", e);
            return "Could not invoke service : " + e.getMessage();
        }
    }

    private interface RAPI {

        @RequestLine("GET /Observations?$top={top}")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        List<Observation> getTopObservations(@Param("top") int top);

        @RequestLine("PUT ")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @Body("{body}")
        void actuate(@Param("body") String body);

        @RequestLine("PUT ")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        @Body("{body}")
        String invokeService(@Param("body") String body);
    }

    private RAPI getClient(String resourceUrl, boolean serverValidation) throws SecurityHandlerException {

        List<AAM> filteredAAMs = findAAMS(resourceUrl);

        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        homePlatformId,
                        username,
                        password,
                        clientId,
                        ComponentIdentifiers.RESOURCE_ACCESS_PROXY,
                        filteredAAMs.get(0).getAamInstanceId(),
                        serverValidation))
                .target(RAPI.class, resourceUrl);
    }

    private RAPI getGuestClient(String resourceUrl, boolean serverValidation) throws SecurityHandlerException {

        List<AAM> filteredAAMs = findAAMS(resourceUrl);

        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.RESOURCE_ACCESS_PROXY,
                        filteredAAMs.get(0).getAamInstanceId(),
                        serverValidation))
                .target(RAPI.class, resourceUrl);
    }

    private List<AAM> findAAMS(String resourceUrl) throws SecurityHandlerException {
        String aamUrl = resourceUrl.replaceAll("/rap.*", "/paam");
        List<AAM> filteredAAMs;

        filteredAAMs = securityHandler.getAvailableAAMs().values().stream()
                .filter(aam -> aam.getAamAddress().equals(aamUrl))
                .collect(Collectors.toList());

        if (filteredAAMs.size() != 1) {
            throw new SecurityHandlerException(String.format("Found %d possible targets instead of only 1", filteredAAMs.size()));
        }

        return filteredAAMs;
    }
}
