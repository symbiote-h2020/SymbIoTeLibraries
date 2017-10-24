package eu.h2020.symbiote.core.internal.cram;

import eu.h2020.symbiote.core.cci.accessNotificationMessages.NotificationMessage;
import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * POJO including NotificationMessages coming from RAP along with the SecurityRequest.
 * @author Vasileios Glykantzis (ICOM)
 */
public class NotificationMessageSecured extends AbstractRequestSecured<NotificationMessage> {

    public NotificationMessageSecured(SecurityRequest securityRequest, NotificationMessage body) {
        super(securityRequest, body);
    }

    public NotificationMessageSecured() {
        // Needed for Jackson serialization
    }
}
