package eu.h2020.symbiote.client;

import eu.h2020.symbiote.core.cci.accessNotificationMessages.NotificationMessage;
import feign.RequestLine;

public interface CRAMRestService {

    @RequestLine("POST " + ClientConstants.PUBLISH_ACCESS_DATA)
    void publishAccessData(NotificationMessage message);
}
