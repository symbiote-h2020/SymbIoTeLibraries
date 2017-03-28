package eu.h2020.symbiote.sh.token;

/**
 * This class wraps an exception that could be thrown during
 * the certificate verification process.
 * 
 * @author Svetlin Nakov
 */
public class TokenVerificationException extends Exception {
    private static final long serialVersionUID = 1L;
 
    public TokenVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public TokenVerificationException(String message) {
        super(message);
    }
}