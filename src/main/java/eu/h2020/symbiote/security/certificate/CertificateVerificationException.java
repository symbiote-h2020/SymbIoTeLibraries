package eu.h2020.symbiote.security.certificate;

import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;

/**
 * TODO R3, delete it and unify with the @{@link eu.h2020.symbiote.security.exceptions.aam.TokenValidationException} by renaming it to ValidationException
 */
public class CertificateVerificationException extends SecurityHandlerException {
    private static final long serialVersionUID = 1L;

    public CertificateVerificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CertificateVerificationException(String message) {
        super(message);
    }
}
