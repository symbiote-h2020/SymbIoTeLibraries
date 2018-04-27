package eu.h2020.symbiote.core.internal.crm;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 * POJO including NotificationMessages coming from RAP along with the SecurityRequest.
 * @author Vasileios Glykantzis (ICOM)
 */
public class MonitoringResponseSecured extends AbstractResponseSecured<Object> {

    public MonitoringResponseSecured(int status, String message, Object body) {
        super(status, message, body);
    }

    public MonitoringResponseSecured() {
        // Needed for Jackson serialization
    }
}
