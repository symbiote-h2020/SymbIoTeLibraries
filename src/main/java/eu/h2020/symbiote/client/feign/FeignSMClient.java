package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.interfaces.SMClient;
import eu.h2020.symbiote.cloud.model.internal.Subscription;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * symbIoTe Subscription Manager client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignSMClient implements SMClient {

    private static final Log logger = LogFactory.getLog(FeignSMClient.class);

    private SubscriptionManagerI smClient;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param homePlatformId    the home Platform Id
     */
    public FeignSMClient(ISecurityHandler securityHandler,
                         String homePlatformId) {

        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();
            String smBaseUrl = availableAAMs.get(homePlatformId).getAamAddress().replace("/paam", "/subscriptionManager");

            this.smClient = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .encoder(new JacksonEncoder())
                    .logger(new ApacheCommonsLogger4Feign(logger))
                    .logLevel(Logger.Level.FULL)
                    .client(new SymbIoTeSecurityHandlerFeignClient())
                    .target(SubscriptionManagerI.class, smBaseUrl);
        } catch (SecurityHandlerException e) {
            logger.error("Could not create FeignSMClient", e);
        }
    }

    @Override
    public void subscribe(Subscription subscription) {
        this.smClient.subscribe(subscription);
    }

    private interface SubscriptionManagerI {
        @RequestLine("POST /subscribe ")
        @Headers({"Accept: application/json", "Content-Type: application/json"})
        void subscribe(Subscription subscription);
    }
}
