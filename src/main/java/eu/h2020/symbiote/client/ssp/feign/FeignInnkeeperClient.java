package eu.h2020.symbiote.client.ssp.feign;

import eu.h2020.symbiote.ssp.model.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssp.model.InnkeeperResourceRegistrationResponse;
import eu.h2020.symbiote.ssp.constants.InnkeeperRestControllerConstants;
import eu.h2020.symbiote.client.ssp.interfaces.InnkeeperClient;
import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.security.commons.ComponentIdentifiers;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.handler.IComponentSecurityHandler;
import feign.*;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static eu.h2020.symbiote.client.feign.SymbIoTeFeignClientFactory.SERVER_VALIDATION_HEADER;

/**
 * symbIoTe Innkeeper client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignInnkeeperClient implements InnkeeperClient {

    private static final Log logger = LogFactory.getLog(FeignInnkeeperClient.class);

    private InnkeeperI innkeeperClient;

    /**
     *
     * @param componentSecurityHandler      the component security handler implementation
     * @param sspId                         the id of the target SSP
     * @param smartSpaceUrl                 the url of the symbIoTe Smart Space
     */
    public FeignInnkeeperClient(IComponentSecurityHandler componentSecurityHandler, String sspId, String smartSpaceUrl) {

        String innkeeperUrl = smartSpaceUrl + InnkeeperRestControllerConstants.INNKEEPER_BASE_PATH;

        this.innkeeperClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new ApacheCommonsLogger4Feign(logger))
                .logLevel(Logger.Level.FULL)
                .client(new SymbIoTeSSPFeignComponentClient(
                        componentSecurityHandler,
                        ComponentIdentifiers.INNKEEPER,
                        sspId))
                .target(InnkeeperI.class, innkeeperUrl);

    }

    @Override
    public InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo, boolean serverValidation) {
        return this.innkeeperClient.registerPlatform(sspRegInfo, serverValidation);
    }

    @Override
    public InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo, boolean serverValidation) {
        return this.innkeeperClient.unregisterPlatform(sspRegInfo, serverValidation);
    }

    @Override
    public InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource, boolean serverValidation) {
        return this.innkeeperClient.registerPlatformResource(resource, serverValidation);
    }


    private interface InnkeeperI {

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_REGISTER_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo,
                                                       @Param("serverValidation") Boolean serverValidation);

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_UNREGISTER_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo,
                                                         @Param("serverValidation") Boolean serverValidation);

        @RequestLine("POST " + InnkeeperRestControllerConstants.INNKEEPER_PLATFORM_JOIN_REQUEST_PATH)
        @Headers({"Accept: application/json", "Content-Type: application/json",
                SERVER_VALIDATION_HEADER + ": {serverValidation}"})
        InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource,
                                                                       @Param("serverValidation") Boolean serverValidation);
    }

}
