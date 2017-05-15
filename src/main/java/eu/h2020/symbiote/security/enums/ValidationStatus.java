package eu.h2020.symbiote.security.enums;

/**
 * Enumeration used as outcome of certificate/token validation procedure
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikołaj Dobski (PSNC)
 */
public enum ValidationStatus {
    /**
     * it is valid
     */
    VALID,

    /**
     * when the entity was validated offline (e.g. token using the provided public key)
     */
    VALID_OFFLINE,

    /**
     * it has reached its end of life
     */
    EXPIRED,

    /**
     * Outcome associated to a certificate/token that was revoked
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
