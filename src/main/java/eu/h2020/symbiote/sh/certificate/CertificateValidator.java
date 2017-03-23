package eu.h2020.symbiote.sh.certificate;

import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.messaging.core.CoreAAMMessageHandler;


public class CertificateValidator {
	private static final Log logger = LogFactory.getLog(CertificateValidator.class);
	X509Certificate coreAAMX509Certificate;
	CoreAAMMessageHandler coreAAM;
	
	public CertificateValidator(CoreAAMMessageHandler coreAAM){
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


    public boolean verifyCertificate(X509Certificate x509Certificate)  {
        try {
        	X509Certificate rootCertificate = getCA();
			x509Certificate.verify(rootCertificate.getPublicKey());
			return true;
		} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException e) {
			logger.error("Could not verify certificate", e);
			return false;
		} catch (SignatureException e) {
			logger.error("The certificate has not been signed by the public key");
			return false;
		}
    }

	
    private X509Certificate getCA() throws CertificateException  {
		if (coreAAMX509Certificate==null){
				coreAAMX509Certificate = coreAAM.getAAMRootCertificate();
		}
    	  return coreAAMX509Certificate;
   	}
}
