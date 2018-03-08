package eu.h2020.symbiote.client;

import eu.h2020.symbiote.core.cci.accessNotificationMessages.NotificationMessage;
import feign.Headers;
import feign.RequestLine;

public interface CRAMRestService {

    @RequestLine("POST " + ClientConstants.PUBLISH_ACCESS_DATA)
    @Headers("Content-Type: application/json")
    void publishAccessData(NotificationMessage message);
}
