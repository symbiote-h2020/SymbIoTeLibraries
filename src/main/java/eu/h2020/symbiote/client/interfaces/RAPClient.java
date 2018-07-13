package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.model.cim.Observation;

import java.util.List;

/**
 * Interface for querying the Platform Resource Access Proxy component
 *
 * @author Vasilis Glykantzis
 */
public interface RAPClient {

    /**
     * Get the latest observation
     *
     * @param resourceUrl       the resource url
     * @param targetPlatformId  the id of the platform owning the resource; used for validation
     * @return                  an {@link Observation}
     */
    Observation getLatestObservation(String resourceUrl, String targetPlatformId);

    /**
     * Get the latest top observations
     *
     * @param resourceUrl       the resource url
     * @param top               the number of observations
     * @param targetPlatformId  the id of the platform owning the resource; used for validation
     * @return                  a list of {@link Observation}
     */
    List<Observation> getTopObservations(String resourceUrl, int top, String targetPlatformId);

    /**
     * Send actuation request
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param targetPlatformId  the id of the platform owning the resource; used for validation
     */
    void actuate(String resourceUrl, String body, String targetPlatformId);

    /**
     * Invoke Service
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param targetPlatformId  the id of the platform owning the resource; used for validation
     * @return                  a response of the service invocation in json format
     */
    String invokeService(String resourceUrl, String body, String targetPlatformId);

    /**
     * Get the latest observation without validating RAP
     *
     * @param resourceUrl   the resource url
     * @return              an {@link Observation}
     */
    Observation getLatestObservationWithoutValidation(String resourceUrl);

    /**
     * Get the latest top observations without validating RAP
     *
     * @param resourceUrl   the resource url
     * @param top           the number of observations
     * @return              a list of {@link Observation}
     */
    List<Observation> getTopObservationsWithoutValidation(String resourceUrl, int top);

    /**
     * Send actuation request without validating RAP
     *
     * @param resourceUrl   the resource url
     * @param body          the message that will be sent
     */
    void actuateWithoutValidation(String resourceUrl, String body);

    /**
     * Invoke Service without validating RAP
     *
     * @param resourceUrl   the resource url
     * @param body          the message that will be sent
     */
    String invokeServiceWithoutValidation(String resourceUrl, String body);
}
