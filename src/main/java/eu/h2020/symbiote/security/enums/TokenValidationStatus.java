package eu.h2020.symbiote.security.enums;

/**
 * Enumeration used as outcome of Token validation procedure
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikołaj Dobski (PSNC)
 */
public enum TokenValidationStatus {
    /**
     * Outcome associated to a token that is still valid after 'Check Home Token Revocation' procedure.
     */
    VALID,

    /**
     * when token has reached its end of life
     */
    EXPIRED,

    /**
     * Outcome associated to a token that is no longer valid after 'Check Home Token Revocation' procedure.
     */
    REVOKED,

    /**
     * when e.g. the signature verification failed
     */
    INVALID,

    /**
     * uninitialized value of this enum
     */
    NULL
}
