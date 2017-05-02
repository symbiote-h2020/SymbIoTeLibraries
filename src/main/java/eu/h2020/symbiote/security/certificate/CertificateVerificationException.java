package eu.h2020.symbiote.security.certificate;

import eu.h2020.symbiote.security.exception.SecurityHandlerException;

public class CertificateVerificationException extends SecurityHandlerException {
    private static final long serialVersionUID = 1L;
 
    public CertificateVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    public CertificateVerificationException(String message) {
        super(message);
    }
}
