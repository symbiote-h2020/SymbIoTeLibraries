package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.PRClient;
import eu.h2020.symbiote.cloud.model.internal.FederationSearchResult;
import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;
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
import java.util.Map;
import java.util.Set;

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.HOME_PLATFORM_IDS_HEADER;
import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * symbIoTe search client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignPRClient implements PRClient {

    private static final Log logger = LogFactory.getLog(FeignPRClient.class);

    private PlatformRegistryI prClient;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param homePlatformId    the home platform id
     */
    public FeignPRClient(ISecurityHandler securityHandler, String homePlatformId) {

        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();

            // The 1st one is left for backwards compatibility
            String prUrl = availableAAMs.get(homePlatformId).getAamAddress()
                    .replace("/paam", "/pr/search")
                    .replace("/aam", "/pr/search");

            this.prClient = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .encoder(new JacksonEncoder())
                    .logger(new ApacheCommonsLogger4Feign(logger))
                    .logLevel(Logger.Level.FULL)
                    .client(new SymbIoTeSecurityHandlerFeignClient(
                            securityHandler,
                            ComponentIdentifiers.PLATFORM_REGISTRY,
                            homePlatformId))
                    .target(PlatformRegistryI.class, prUrl);

        } catch (SecurityHandlerException e) {
            logger.error("Could not create FeignPRClient", e);
        }

    }

    @Override
    public FederationSearchResult search(PlatformRegistryQuery query, boolean serverValidation, Set<String> homePlatformIds) {
        return prClient.query(query.buildRequestParametersMap(), serverValidation, homePlatformIds);
    }

    @Override
    public FederationSearchResult searchAsGuest(PlatformRegistryQuery query, boolean serverValidation) {
        return prClient.query(query.buildRequestParametersMap(), serverValidation, new HashSet<>());
    }

    private interface PlatformRegistryI {

        @RequestLine("GET ")
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}", HOME_PLATFORM_IDS_HEADER + ": {homePlatformIds}"})
        FederationSearchResult query(@QueryMap Map<String, String> queryMap,
                                     @Param("serverValidation") Boolean serverValidation,
                                     @Param("homePlatformIds") Set<String> homePlatformIds);
    }
}
