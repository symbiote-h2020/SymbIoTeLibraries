package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.commons.SecurityConstants;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.CORE_INTERFACE_PATH;

/**
 * symbIoTe Core Resource Access Manager client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignCRAMClient implements CRAMClient {

    private static final Log logger = LogFactory.getLog(FeignCRAMClient.class);
    private static final String GET_RESOURCE_URLS_BASE_PATH = CORE_INTERFACE_PATH + "/resourceUrls?";

    private final CRAMI cramClient;
    private final CRAMI cramClientWithoutValidation;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param coreAddress       the base address of the symbIoTe core
     * @param homePlatformId    the home Platform Id
     * @param username          the username in the home platform
     * @param password          the password in the home platform
     * @param clientId          the client id
     */
    public FeignCRAMClient(ISecurityHandler securityHandler,
                           String coreAddress,
                           String homePlatformId,
                           String username,
                           String password,
                           String clientId) {

        this.cramClient = Feign.builder()
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
                        ComponentIdentifiers.CORE_RESOURCE_ACCESS_MONITOR,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        true))
                .target(CRAMI.class, coreAddress);

        this.cramClientWithoutValidation = Feign.builder()
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
                        ComponentIdentifiers.CORE_RESOURCE_ACCESS_MONITOR,
                        SecurityConstants.CORE_AAM_INSTANCE_ID,
                        false))
                .target(CRAMI.class, coreAddress);

    }

    @Override
    public ResourceUrlsResponse getResourceUrl(String resourceId) {
        return cramClient.getResourceUrls(new ArrayList<>(Collections.singleton(resourceId)));
    }

    @Override
    public ResourceUrlsResponse getResourceUrl(List<String> resourceIds) {
        return cramClient.getResourceUrls(resourceIds);
    }

    @Override
    public ResourceUrlsResponse getResourceUrlWithoutValidation(String resourceId) {
        return cramClientWithoutValidation.getResourceUrls(new ArrayList<>(Collections.singleton(resourceId)));
    }

    @Override
    public ResourceUrlsResponse getResourceUrlWithoutValidation(List<String> resourceIds) {
        return cramClientWithoutValidation.getResourceUrls(resourceIds);
    }

    private interface CRAMI {

        @RequestLine("GET " + GET_RESOURCE_URLS_BASE_PATH + "id={ids}")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        ResourceUrlsResponse getResourceUrls(@Param("ids") List<String> ids);
    }
}
