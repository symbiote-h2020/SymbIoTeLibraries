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

import java.util.Map;

/**
 * symbIoTe search client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignPRClient implements PRClient {

    private static final Log logger = LogFactory.getLog(FeignPRClient.class);

    private PlatformRegistryI prClient;
    private PlatformRegistryI prClientWithoutValidation;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param homePlatformId    the home Platform Id
     * @param username          the username in the home platform
     * @param password          the password in the home platform
     * @param clientId          the client id
     */
    public FeignPRClient(ISecurityHandler securityHandler,
                         String homePlatformId,
                         String username,
                         String password,
                         String clientId) {

        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();
            String prUrl = availableAAMs.get(homePlatformId).getAamAddress().replace("/paam", "/pr/search");

            this.prClient = Feign.builder()
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
                            ComponentIdentifiers.PLATFORM_REGISTRY,
                            homePlatformId,
                            true))
                    .target(PlatformRegistryI.class, prUrl);

            this.prClientWithoutValidation = Feign.builder()
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
                            ComponentIdentifiers.PLATFORM_REGISTRY,
                            homePlatformId,
                            false))
                    .target(PlatformRegistryI.class, prUrl);
        } catch (SecurityHandlerException e) {
            logger.error("Could not create FeignPRClient", e);
        }

    }

    @Override
    public FederationSearchResult search(PlatformRegistryQuery query) {
        return prClient.query(query.buildRequestParametersMap());
    }

    @Override
    public FederationSearchResult searchWithoutValidation(PlatformRegistryQuery query) {
        return prClientWithoutValidation.query(query.buildRequestParametersMap());
    }

    private interface PlatformRegistryI {

        @RequestLine("GET ")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        FederationSearchResult query(@QueryMap Map<String, String> queryMap);
    }
}
