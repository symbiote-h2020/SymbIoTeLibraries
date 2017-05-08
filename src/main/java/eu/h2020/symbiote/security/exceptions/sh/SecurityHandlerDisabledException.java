package eu.h2020.symbiote.security.exceptions.sh;

import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;

public class SecurityHandlerDisabledException extends SecurityHandlerException {
    private static final long serialVersionUID = 1L;

    public SecurityHandlerDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityHandlerDisabledException(String message) {
        super(message);
    }
}
