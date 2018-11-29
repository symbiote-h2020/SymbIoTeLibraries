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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.HOME_PLATFORM_IDS_HEADER;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;
import eu.h2020.symbiote.client.AbstractSemanticRAPClient;
import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.client.interfaces.SearchClient;

/**
 * symbIoTe RAP client based on Feign
 *
 * @author Vasilis Glykantzis, Michael Jacoby
 */
public class FeignRAPClient extends AbstractSemanticRAPClient implements RAPClient {

    private static final Log logger = LogFactory.getLog(FeignRAPClient.class);

    private final ISecurityHandler securityHandler;

    /**
     * Instances created via this constructor do not support semantic RAP client
     * interface
     *
     * @param securityHandler the security handler implementation that is going
     * to be used
     */
    public FeignRAPClient(ISecurityHandler securityHandler) {
        super(null, null);
        super.setRAPClient(this);
        this.securityHandler = securityHandler;
    }

    /**
     *
     * @param securityHandler the security handler implementation that is going
     * to be used
     * @param cramClient the CRAM client needed for resource access URL
     * resolution
     * @param searchClient
     */
    public FeignRAPClient(ISecurityHandler securityHandler, CRAMClient cramClient, SearchClient searchClient) {
        super(cramClient, searchClient);
        super.setRAPClient(this);
        this.securityHandler = securityHandler;
    }

    @Override
    public void actuate(String resourceUrl, String body, boolean serverValidation, Set<String> homePlatformIds) {
        try {
            getClient(resourceUrl).actuate(body, serverValidation, homePlatformIds);
        } catch (SecurityHandlerException e) {
            logger.error("Could not send actuation request", e);
        }
    }

    @Override
    public void actuateAsGuest(String resourceUrl, String body, boolean serverValidation) {
        try {
            getClient(resourceUrl).actuate(body, serverValidation, new HashSet<>());
        } catch (SecurityHandlerException e) {
            logger.error("Could not send actuation request", e);
        }
    }

    @Override
    public Observation getLatestObservation(String resourceUrl, boolean serverValidation, Set<String> homePlatformIds) {
        try {
            return getClient(resourceUrl).getTopObservations(1, serverValidation, homePlatformIds).get(0);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get latest Observation", e);
            return null;
        }
    }

    @Override
    public Observation getLatestObservationAsGuest(String resourceUrl, boolean serverValidation) {
        try {
            return getClient(resourceUrl).getTopObservations(1, serverValidation, new HashSet<>()).get(0);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get latest Observation", e);
            return null;
        }
    }

    @Override
    public String getResource(String resourceId, boolean serverValidation, Set<String> homePlatformIds) throws SecurityHandlerException {
        return getClient(getCramClient().getResourceUrl(resourceId, true, homePlatformIds).getBody().get(resourceId)).getResource(serverValidation, homePlatformIds);
    }

    @Override
    public String getResourceAsGuest(String resourceId, boolean serverValidation) throws SecurityHandlerException {
        return getClient(getCramClient().getResourceUrlAsGuest(resourceId, true).getBody().get(resourceId)).getResource(serverValidation, new HashSet<>());
    }

    @Override
    public List<Observation> getTopObservations(String resourceUrl, int top, boolean serverValidation, Set<String> homePlatformIds) {
        try {
            return getClient(resourceUrl).getTopObservations(top, serverValidation, homePlatformIds);
        } catch (SecurityHandlerException e) {
            logger.error("Could not get Observations", e);
            return null;
        }
    }

    @Override
    public List<Observation> getTopObservationsAsGuest(String resourceUrl, int top, boolean serverValidation) {
        try {
            return getClient(resourceUrl).getTopObservations(top, serverValidation, new HashSet<>());
        } catch (SecurityHandlerException e) {
            logger.error("Could not get Observations", e);
            return null;
        }
    }

    @Override
    public String invokeService(String resourceUrl, String body, boolean serverValidation, Set<String> homePlatformIds) {
        try {
            long startTime = System.nanoTime();
            String result = getClient(resourceUrl).invokeService(body, serverValidation, homePlatformIds);
            long stopTime = System.nanoTime();
            logger.info("###TIME###   invokeService - " + (stopTime - startTime));
            return result;
        } catch (SecurityHandlerException e) {
            logger.error("Could not invoke service", e);
            return "Could not invoke service : " + e.getMessage();
        }
    }

    @Override
    public String invokeServiceAsGuest(String resourceUrl, String body, boolean serverValidation) {
        try {
            long startTime = System.nanoTime();
            String result = getClient(resourceUrl).invokeService(body, serverValidation, new HashSet<>());
            long stopTime = System.nanoTime();
            logger.info("###TIME###   invokeService - " + (stopTime - startTime));
            return result;
        } catch (SecurityHandlerException e) {
            logger.error("Could not invoke service", e);
            return "Could not invoke service : " + e.getMessage();

        }
    }

    private List<AAM> findAAMS(String resourceUrl) throws SecurityHandlerException {
        String aamUrl = resourceUrl.replaceAll("/rap.*", "/aam");
        List<AAM> filteredAAMs = securityHandler.getAvailableAAMs().values().stream()
                .filter(aam -> aam.getAamAddress().equals(aamUrl))
                .collect(Collectors.toList());

        if (filteredAAMs.size() != 1) {
            // Search with /paam with was the path in 2.x of symbIoTe

            String aamUrlV2 = resourceUrl.replaceAll("/rap.*", "/paam");
            filteredAAMs = securityHandler.getAvailableAAMs().values().stream()
                    .filter(aam -> aam.getAamAddress().equals(aamUrlV2))
                    .collect(Collectors.toList());

            if (filteredAAMs.size() != 1) {
                throw new SecurityHandlerException(String.format("Found %d possible targets instead of only 1", filteredAAMs.size()));
            }
        }

        return filteredAAMs;
    }

    RAPI getClient(String resourceUrl) throws SecurityHandlerException {

        List<AAM> filteredAAMs = findAAMS(resourceUrl);

        return Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.RESOURCE_ACCESS_PROXY,
                        filteredAAMs.get(0).getAamInstanceId()))
                .target(RAPI.class,
                        resourceUrl);
    }

    interface RAPI {

        @RequestLine("GET /")
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        String getResource(@Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);

        @RequestLine("GET /Observations?$top={top}")
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        List<Observation> getTopObservations(@Param("top") int top,
                @Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);

        @RequestLine("PUT ")
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        @Body("{body}")
        void actuate(@Param("body") String body,
                @Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);

        @RequestLine("PUT ")
        @Headers({"Accept: application/json", "Content-Type: application/json",
            SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        @Body("{body}")
        String invokeService(@Param("body") String body,
                @Param("serverValidation") Boolean serverValidation,
                @Param("homePlatformIds") Set<String> homePlatformIds);
    }

}
