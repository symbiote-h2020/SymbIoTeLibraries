package eu.h2020.symbiote.commons.security.token;

import eu.h2020.symbiote.commons.security.SecurityHandlerException;

public class TokenVerificationException extends SecurityHandlerException {
    private static final long serialVersionUID = 1L;
 
    public TokenVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public TokenVerificationException(String message) {
        super(message);
    }
}
