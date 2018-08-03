package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.RegistrationHandlerClient;
import eu.h2020.symbiote.client.interfaces.RHClient;
import eu.h2020.symbiote.cloud.model.internal.CloudResource;
import eu.h2020.symbiote.cloud.model.internal.RdfCloudResourceList;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.security.communication.ApacheCommonsLogger4Feign;
import eu.h2020.symbiote.security.communication.payloads.AAM;
import eu.h2020.symbiote.security.handler.ISecurityHandler;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * symbIoTe Registration Handler client based on Feign
 *
 * @author Vasilis Glykantzis
 */
public class FeignRHClient implements RHClient {

    private static final Log logger = LogFactory.getLog(FeignRHClient.class);

    private RegistrationHandlerClient rhClient;

    /**
     *
     * @param securityHandler   the security handler implementation that is going to be used
     * @param homePlatformId    the home Platform Id
     */
    public FeignRHClient(ISecurityHandler securityHandler,
                         String homePlatformId) {

        try {
            Map<String, AAM> availableAAMs = securityHandler.getAvailableAAMs();

            // The 1st one is left for backwards compatibility
            String rhBaseUrl = availableAAMs.get(homePlatformId).getAamAddress()
                    .replace("/paam", "/rh")
                    .replace("/aam", "/rh");

            this.rhClient = Feign.builder()
                    .decoder(new JacksonDecoder())
                    .encoder(new JacksonEncoder())
                    .logger(new ApacheCommonsLogger4Feign(logger))
                    .logLevel(Logger.Level.FULL)
                    .client(new SymbIoTeSecurityHandlerFeignClient())
                    .target(RegistrationHandlerClient.class, rhBaseUrl);
        } catch (SecurityHandlerException e) {
            logger.error("Could not create FeignRHClient", e);
        }
    }

    @Override
    public List<CloudResource> getResources() {
        return rhClient.getResources();
    }

    @Override
    public CloudResource getResource(String resourceInternalId) {
        return rhClient.getResource(resourceInternalId);
    }

    @Override
    public CloudResource addL1Resource(CloudResource resource) {
        return rhClient.addResource(resource);
    }

    @Override
    public List<CloudResource> addL1Resources(List<CloudResource> resources) {
        return rhClient.addResources(resources);
    }

    @Override
    public List<CloudResource> addL1RdfResources(RdfCloudResourceList resources) {
        return rhClient.addRdfResources(resources);
    }

    @Override
    public CloudResource updateL1Resource(CloudResource resource) {
        return rhClient.updateResource(resource);
    }

    @Override
    public List<CloudResource> updateL1Resources(List<CloudResource> resources) {
        return rhClient.updateResources(resources);
    }

    @Override
    public List<CloudResource> sync() {
        return rhClient.sync();
    }

    @Override
    public CloudResource deleteL1Resource(String resourceInternalId) {
        return rhClient.deleteResource(resourceInternalId);
    }

    @Override
    public List<CloudResource> deleteL1Resources(List<String> resourceInternalIds) {
        return rhClient.deleteResources(resourceInternalIds);
    }

    @Override
    public void clearResources() {
        rhClient.clearResources();
    }

    @Override
    public List<CloudResource> addL2Resources(List<CloudResource> cloudResources) {
        return rhClient.addLocalResources(cloudResources);
    }

    @Override
    public List<CloudResource> updateL2Resources(List<CloudResource> cloudResources) {
        return rhClient.updateLocalResources(cloudResources);
    }

    @Override
    public List<String> removeL2Resources(List<String> resourceInternalIds) {
        return rhClient.removeLocalResources(resourceInternalIds);
    }

    @Override
    public Map<String, List<CloudResource>> shareL2Resources(Map<String, Map<String, Boolean>> input) {
        return rhClient.shareResources(input);
    }

    @Override
    public Map<String, List<CloudResource>> unshareL2Resources(Map<String, List<String>> input) {
        return rhClient.unshareResources(input);
    }
}
