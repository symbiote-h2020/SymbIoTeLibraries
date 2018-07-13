package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.model.cim.Observation;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

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
    public Observation getLatestObservation(String resourceUrl, String targetPlatformId) {
        return getClient(resourceUrl, targetPlatformId).getTopObservations(1).get(0);
    }

    @Override
    public List<Observation> getTopObservations(String resourceUrl, int top, String targetPlatformId) {
        return getClient(resourceUrl, targetPlatformId).getTopObservations(top);
    }

    @Override
    public void actuate(String resourceUrl, String body, String targetPlatformId) {
        getClient(resourceUrl, targetPlatformId).actuate(body);
    }

    @Override
    public String invokeService(String resourceUrl, String body, String targetPlatformId) {
        return getClient(resourceUrl, targetPlatformId).invokeService(body);
    }

    @Override
    public Observation getLatestObservationWithoutValidation(String resourceUrl) {
        return getClientWithoutValidation(resourceUrl).getTopObservations(1).get(0);
    }

    @Override
    public List<Observation> getTopObservationsWithoutValidation(String resourceUrl, int top) {
        return getClientWithoutValidation(resourceUrl).getTopObservations(top);
    }

    @Override
    public void actuateWithoutValidation(String resourceUrl, String body) {
        getClientWithoutValidation(resourceUrl).actuate(body);
    }

    @Override
    public String invokeServiceWithoutValidation(String resourceUrl, String body) {
        return getClientWithoutValidation(resourceUrl).invokeService(body);
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

    private RAPI getClient(String resourceUrl, String targetPlatformId) {
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
                        targetPlatformId,
                        true))
                .target(RAPI.class, resourceUrl);
    }

    private RAPI getClientWithoutValidation(String resourceUrl) {
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
                        "",
                        false))
                .target(RAPI.class, resourceUrl);
    }
}
