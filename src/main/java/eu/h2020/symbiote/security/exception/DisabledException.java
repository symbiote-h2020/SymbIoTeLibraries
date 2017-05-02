package eu.h2020.symbiote.security.exception;

public class DisabledException extends SecurityHandlerException {
    private static final long serialVersionUID = 1L;
 
    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public DisabledException(String message) {
        super(message);
    }
}
