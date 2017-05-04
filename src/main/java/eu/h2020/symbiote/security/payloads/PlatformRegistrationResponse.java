package eu.h2020.symbiote.security.payloads;

import eu.h2020.symbiote.security.certificate.Certificate;

/**
 * Describes a response for platform registration sent by AAM
 *
 * @author Maksymilian Marcinowski (PSNC)
 * @author Mikołaj Dobski (PSNC)
 */
public class PlatformRegistrationResponse {
    private Certificate platformOwnerCertificate = new Certificate();
    private String platformOwnerPrivateKey = "";
    private String platformId = "";

    public PlatformRegistrationResponse() {
        // used by serializer
    }

    public PlatformRegistrationResponse(Certificate platformOwnerCertificate, String platformOwnerPrivateKey, String
            registeredPlatformId) {
        this.platformOwnerCertificate = platformOwnerCertificate;
        this.platformOwnerPrivateKey = platformOwnerPrivateKey;
        this.platformId = registeredPlatformId;
    }

    /**
     * @return Platform Owner certificate to be used for interaction with symbIoTe core components.
     */
    public Certificate getPlatformOwnerCertificate() {
        return platformOwnerCertificate;
    }

    public void setPlatformOwnerCertificate(Certificate platformOwnerCertificate) {
        this.platformOwnerCertificate = platformOwnerCertificate;
    }

    /**
     * @return in PEM format
     */
    public String getPlatformOwnerPrivateKey() {
        return platformOwnerPrivateKey;
    }

    public void setPlatformOwnerPrivateKey(String platformOwnerPrivateKey) {
        this.platformOwnerPrivateKey = platformOwnerPrivateKey;
    }

    /**
     * @return platform identifier for given platform owner, to be used later in Registry
     */
    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
}
