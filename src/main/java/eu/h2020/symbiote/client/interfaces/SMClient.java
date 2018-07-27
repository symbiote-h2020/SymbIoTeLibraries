package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.cloud.model.internal.Subscription;

import java.util.List;

/**
 * Interface for querying the platform Subscription Manager component
 *
 * @author Vasilis Glykantzis
 */
public interface SMClient {

    /**
     * Queries the Subscription Manager component
     *
     * @param subscription      the subscription request send to the Subscription Manager
     */
    void subscribe(Subscription subscription);

    /**
     * Queries the Subscription Manager component about the current subscriptions
     *
     * @return the list of current subscriptions
     */
    List<Subscription> getAllSubscriptions();

    /**
     * Queries Subscription Manager for the subscription of the specified platform
     *
     * @param platformId    the id of the platform
     * @return              the current platform subscription
     */
    Subscription getPlatformSubscription(String platformId);
}
