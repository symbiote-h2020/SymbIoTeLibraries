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
import java.util.HashSet;
import java.util.Set;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.CORE_INTERFACE_PATH;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.HOME_PLATFORM_IDS_HEADER;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * symbIoTe Core Resource Access Manager client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignCRAMClient implements CRAMClient {

    private static final Log logger = LogFactory.getLog(FeignCRAMClient.class);
    private static final String GET_RESOURCE_URLS_BASE_PATH = CORE_INTERFACE_PATH + "/resourceUrls?";

    private final CRAMI cramClient;


    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param coreAddress       the base address of the symbIoTe core
     */
    public FeignCRAMClient(ISecurityHandler securityHandler, String coreAddress) {

        this.cramClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSecurityHandlerFeignClient(
                        securityHandler,
                        ComponentIdentifiers.CORE_RESOURCE_ACCESS_MONITOR,
                        SecurityConstants.CORE_AAM_INSTANCE_ID))
                .target(CRAMI.class, coreAddress);

    }

    @Override
    public ResourceUrlsResponse getResourceUrl(String resourceId, boolean serverValidation, Set<String> homePlatformIds) {
        return cramClient.getResourceUrls(
                new HashSet<>(new ArrayList<>(Collections.singletonList(resourceId))),
                serverValidation,
                homePlatformIds);
    }

    @Override
    public ResourceUrlsResponse getResourceUrl(Set<String> resourceIds, boolean serverValidation, Set<String> homePlatformIds) {
        return cramClient.getResourceUrls(resourceIds, serverValidation, homePlatformIds);
    }

    @Override
    public ResourceUrlsResponse getResourceUrlAsGuest(String resourceId, boolean serverValidation) {
        return cramClient.getResourceUrls(
                new HashSet<>(new ArrayList<>(Collections.singletonList(resourceId))),
                serverValidation,
                new HashSet<>());
    }

    @Override
    public ResourceUrlsResponse getResourceUrlAsGuest(Set<String> resourceIds, boolean serverValidation) {
        return cramClient.getResourceUrls(resourceIds, serverValidation, new HashSet<>());
    }

    private interface CRAMI {

        @RequestLine("GET " + GET_RESOURCE_URLS_BASE_PATH + "id={ids}")
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        ResourceUrlsResponse getResourceUrls(@Param("ids") Set<String> ids,
                                             @Param("serverValidation") Boolean serverValidation,
                                             @Param("homePlatformIds") Set<String> homePlatformIds);
    }
}
