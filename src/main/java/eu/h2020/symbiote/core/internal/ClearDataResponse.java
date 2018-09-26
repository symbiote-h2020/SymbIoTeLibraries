package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 * Core internal payload of clear data message.
 *
 * Created by Szymon Mueller on 09/11/2017.
 */
public class ClearDataResponse extends AbstractResponseSecured<String> {

    public ClearDataResponse() {

    }

    public ClearDataResponse(int status, String message, String body) {
        super(status,message,body);
    }

}
