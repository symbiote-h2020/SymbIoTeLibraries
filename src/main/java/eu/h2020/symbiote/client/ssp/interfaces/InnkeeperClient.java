package eu.h2020.symbiote.client.ssp.interfaces;

import eu.h2020.symbiote.ssp.model.InnkeeperRegistrationResponse;
import eu.h2020.symbiote.ssp.model.InnkeeperResourceRegistrationResponse;
import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.cloud.model.ssp.SspResource;

public interface InnkeeperClient {

    /**
     * Registers a local platform in the SSP Innkeeper
     *
     * @param sspRegInfo            registration information
     * @param serverValidation      if true it will validate the Platform Registry Component
     * @return                      the registration result
     */
    InnkeeperRegistrationResponse registerPlatform(SspRegInfo sspRegInfo, boolean serverValidation);

    /**
     * Unregisters a local platform from the SSP Innkeeper
     *
     * @param sspRegInfo            unregistration information
     * @param serverValidation      if true it will validate the Platform Registry Component
     * @return                      the unregistration result
     */
    InnkeeperRegistrationResponse unregisterPlatform(SspRegInfo sspRegInfo, boolean serverValidation);

    /**
     * Registers a platform resource in the SSP Innkeeper
     * @param resource              the resource description
     * @param serverValidation      if true it will validate the Platform Registry Component
     * @return                      the registration result
     */
    InnkeeperResourceRegistrationResponse registerPlatformResource(SspResource resource, boolean serverValidation);
}
