package eu.h2020.symbiote.commons.security;


public class SecurityHandlerException extends Exception {
    private static final long serialVersionUID = 1L;
 
    public SecurityHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public SecurityHandlerException(String message) {
        super(message);
    }
}
