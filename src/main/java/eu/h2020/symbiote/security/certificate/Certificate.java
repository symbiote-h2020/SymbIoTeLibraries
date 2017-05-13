package eu.h2020.symbiote.security.certificate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * SymbIoTe certificate with stored PEM value
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikołaj Dobski (PSNC)
 */
public class Certificate {

    @Id
    private String certificateString = "";

    /**
     * required by JPA
     */
    public Certificate() {
        ECDSAHelper.enableECDSAProvider();
    }

    /**
     * @param certificateString in PEM format
     */
    public Certificate(String certificateString) {
        ECDSAHelper.enableECDSAProvider();
        this.certificateString = certificateString;
    }

    /**
     * @return retrieve the X509 certificate that corresponds to the stored string
     * @throws CertificateException on internal PEM string value to {@link X509Certificate} conversion (e.g. string value empty)
     */
    @JsonIgnore
    public X509Certificate getX509() throws CertificateException {
        if (certificateString.isEmpty())
            throw new CertificateException("internal PEM certificate is not initialized");
        ECDSAHelper.enableECDSAProvider();
        InputStream stream = new ByteArrayInputStream(this.getCertificateString().getBytes(StandardCharsets.UTF_8));
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        return (X509Certificate) cf.generateCertificate(stream);
    }

    /**
     * @return in PEM format
     */
    public String getCertificateString() {
        return certificateString;
    }

    /**
     * @param certificateString in PEM format
     * @throws CertificateException for empty string
     */
    public void setCertificateString(String certificateString) throws CertificateException {
        /* TODO R3, once AAM knows the certificates, we can enforce this check
        if (certificateString == null || certificateString.isEmpty())
            throw new CertificateException("trying to pass empty value");
        */
        this.certificateString = certificateString;
    }

    @Override
    public String toString() {
        return this.certificateString;
    }
}
