package eu.h2020.symbiote.core.internal.cram;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;
import eu.h2020.symbiote.core.cci.accessNotificationMessages.NotificationMessage;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * POJO including NotificationMessages coming from RAP along with the SecurityRequest.
 * @author Vasileios Glykantzis (ICOM)
 */
public class NotificationMessageResponseSecured extends AbstractResponseSecured<Object> {

    public NotificationMessageResponseSecured() {
        // Needed for Jackson serialization
    }
}
