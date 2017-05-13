package eu.h2020.symbiote.security.certificate;

import eu.h2020.symbiote.security.rest.clients.CoreAAMClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;


public class CertificateValidator {
    private static final Log logger = LogFactory.getLog(CertificateValidator.class);

    private X509Certificate coreAAMX509Certificate;
    private CoreAAMClient coreAAM;

    public CertificateValidator(CoreAAMClient coreAAM) {
        this.coreAAM = coreAAM;
    }

    public boolean validate(KeyStore p12Certificate) throws CertificateVerificationException {
        boolean result = false;
        try {
            Enumeration<String> e = p12Certificate.aliases();
            if (e.hasMoreElements()) {
                String alias = e.nextElement();
                X509Certificate x509Certificate = (X509Certificate) p12Certificate.getCertificate(alias);
                result = verifyCertificate(x509Certificate);
            }
            if (e.hasMoreElements())
                throw new CertificateVerificationException("The p12 store is containing more than 1 certificate, only one should be contained.");
        } catch (KeyStoreException ex) {
            throw new CertificateVerificationException("Error validating ceriticate", ex);
        }
        return result;
    }


    public boolean verifyCertificate(X509Certificate x509Certificate) {
        try {
            X509Certificate rootCertificate = getCA();
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            x509Certificate.verify(rootCertificate.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME);
            return true;
        } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException e) {
            logger.error("Could not verify certificate", e);
            return false;
        } catch (SignatureException e) {
            logger.error("The certificate has not been signed by the public key", e);
            return false;
        }
    }


    private X509Certificate getCA() throws CertificateException {
        if (coreAAMX509Certificate == null) {
            coreAAMX509Certificate = coreAAM.getAAMRootCertificate();
        }
        return coreAAMX509Certificate;
    }
}

