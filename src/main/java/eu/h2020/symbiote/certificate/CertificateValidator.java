package eu.h2020.symbiote.certificate;

import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.core.CoreAAMMessageHandler;
@Component
public class CertificateValidator {
	private static final Log logger = LogFactory.getLog(CertificateValidator.class);
	X509Certificate coreAAMX509Certificate; 
	
	public CertificateValidator(){
	}
	
	public boolean validate(KeyStore p12Certificate) throws CertificateVerificationException {
		try {
			Enumeration<String> e = p12Certificate.aliases();
		
	        while (e.hasMoreElements()) {
	            String alias = e.nextElement();
	            X509Certificate x509Certificate = (X509Certificate) p12Certificate.getCertificate(alias);
	            verifyCertificate(x509Certificate);
	        }
	        if (e.hasMoreElements()){
	        	logger.error("The p12 store is containing more than 1 certificate, only one should be contained.");
	        	return false;
	        }else{
	        	return true;
	        }
		} catch (KeyStoreException ex) {
			throw new CertificateVerificationException("Error validating ceriticate", ex);
		}
	}


    public boolean verifyCertificate(X509Certificate x509Certificate)  {
        try {
        	X509Certificate rootCertificate = getCA();
			x509Certificate.verify(rootCertificate.getPublicKey());
			return true;
		} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e) {
			logger.error("Could not verify certificate", e);
			return false;
		}
    }

	
    private X509Certificate getCA() throws CertificateException  {
		if (coreAAMX509Certificate==null){
				CoreAAMMessageHandler coreAAM = new CoreAAMMessageHandler();
				coreAAMX509Certificate = coreAAM.getCoreAAMRootCertificate();
		}
    	  return coreAAMX509Certificate;
   	}
    
    
}
