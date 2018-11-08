package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.client.serialization.SerializationHelper;
import eu.h2020.symbiote.model.cim.Observation;

import java.util.List;
import java.util.Set;

/**
 * Interface for querying the Platform Resource Access Proxy (RAP) component
 *
 * @author Vasilis Glykantzis
 */
public interface RAPClient {

    /**
     * Get the latest observation
     *
     * @param resourceUrl       the resource url
     * @param serverValidation  if true it will validate RAP
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  an {@link Observation}
     */
    Observation getLatestObservation(String resourceUrl, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Get the latest top observations
     *
     * @param resourceUrl       the resource url
     * @param top               the number of observations
     * @param serverValidation  if true it will validate RAP
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  a list of {@link Observation}
     */
    List<Observation> getTopObservations(String resourceUrl, int top, boolean serverValidation, Set<String> homePlatformIds);

    /**
     * Send actuation request
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param serverValidation  if true it will validate RAP
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     */
    void actuate(String resourceUrl, String body, boolean serverValidation, Set<String> homePlatformIds);
    
    /**
     * Invoke Service
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param serverValidation  if true it will validate RAP
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  a response of the service invocation in json format
     */
    String invokeService(String resourceUrl, String body, boolean serverValidation, Set<String> homePlatformIds);
    
    /**
     * Invoke Service
     *
     * @param resourceUrl       the resource url
     * @param parameter         parameters to be sent to service as Java object
     * @param serverValidation  if true it will validate RAP
     * @param homePlatformIds   a set of home platform ids from which we are going to get credentials for the request
     * @return                  a response of the service invocation in json format
     */
    default String invokeService(String resourceUrl, Object parameter, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeService(resourceUrl, SerializationHelper.serializeServiceParameter(parameter), serverValidation, homePlatformIds);
    }

    /**
     * Get the latest observation without validating RAP
     *
     * @param resourceUrl       the resource url
     * @param serverValidation  if true it will validate RAP
     * @return                  an {@link Observation}
     */
    Observation getLatestObservationAsGuest(String resourceUrl, boolean serverValidation);

    /**
     * Get the latest top observations without validating RAP
     *
     * @param resourceUrl       the resource url
     * @param top               the number of observations
     * @param serverValidation  if true it will validate RAP
     * @return                  a list of {@link Observation}
     */
    List<Observation> getTopObservationsAsGuest(String resourceUrl, int top, boolean serverValidation);

    /**
     * Send actuation request without validating RAP
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param serverValidation  if true it will validate RAP
     */
    void actuateAsGuest(String resourceUrl, String body, boolean serverValidation);

    /**
     * Invoke Service without validating RAP
     *
     * @param resourceUrl       the resource url
     * @param body              the message that will be sent
     * @param serverValidation  if true it will validate RAP
     */
    String invokeServiceAsGuest(String resourceUrl, String body, boolean serverValidation);
}
