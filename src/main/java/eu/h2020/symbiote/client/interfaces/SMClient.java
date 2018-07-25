package eu.h2020.symbiote.client.interfaces;

import eu.h2020.symbiote.cloud.model.internal.Subscription;

/**
 * Interface for querying the platform Subscription Manager component
 *
 * @author Vasilis Glykantzis
 */
public interface SMClient {

    /**
     * Queries the Platform Registry component with home token
     *
     * @param subscription      the subscription request send to the Subscription Manager
     */
    void subscribe(Subscription subscription);
}
