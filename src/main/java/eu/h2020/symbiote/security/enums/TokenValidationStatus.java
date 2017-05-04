package eu.h2020.symbiote.security.enums;

/**
 * Enumeration used as outcome in AAM 'Check Token Revocation' procedure.
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
     * Outcome associated to a token that is no longer valid after 'Check Home Token Revocation' procedure.
     */
    REVOKED,

    /**
     * uninitialized value of this enum
     */
    NULL
}
