package eu.h2020.symbiote.certificate;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertStore;
import java.security.cert.CertificateException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.core.CoreAAMMessageHandler;
@Component
public class CertificateValidator {
	private static final Log logger = LogFactory.getLog(CertificateValidator.class);

	public CertificateValidator(){
		CoreAAMMessageHandler coreAAM = new CoreAAMMessageHandler();
		coreAAM.getPublicRootCertificate();
	}
	
	public boolean validate(KeyStore p12Certificate) throws CertificateVerificationException {
		try {
			Enumeration<String> e = p12Certificate.aliases();
	        while (e.hasMoreElements()) {
	            String alias = e.nextElement();
	            X509Certificate x509Certificate = (X509Certificate) p12Certificate.getCertificate(alias);
	            verifyCertificate(x509Certificate);
	        }
		} catch (KeyStoreException ex) {
			throw new CertificateVerificationException("Error validating ceriticate", ex);
		}
		return false;
		/*TBD	TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init((KeyStore)null);
		

		for (TrustManager trustManager: trustManagerFactory.getTrustManagers()) {  
		    if (trustManager instanceof X509TrustManager) {  
		        X509TrustManager x509TrustManager = (X509TrustManager)trustManager;  
		        x509TrustManager.checkServerTrusted(...);
		    }  
		}*/		
	}


    public static PKIXCertPathBuilderResult verifyCertificate(X509Certificate x509Certificate)
            throws CertificateVerificationException {
            try {
                // Check for self-signed certificate
                if (isSelfSigned(x509Certificate)) {
                    throw new CertificateVerificationException(
                        "The certificate is self-signed.");
                }
                 
                // Prepare a set of trusted root CA certificates
                // and a set of intermediate certificates
                Set<X509Certificate> trustedRootCerts = new HashSet<X509Certificate>();
                Set<X509Certificate> intermediateCerts = new HashSet<X509Certificate>();
/*                for (X509Certificate additionalCert : additionalCerts) {
                    if (isSelfSigned(additionalCert)) {
                        trustedRootCerts.add(additionalCert);
                    } else {
                        intermediateCerts.add(additionalCert);
                    }
                }*/
                 
                // Attempt to build the certification chain and verify it
                PKIXCertPathBuilderResult verifiedCertChain = 
                    verifyCertificate(x509Certificate, trustedRootCerts, intermediateCerts);
                 
                // Check whether the certificate is revoked by the CRL
                // given in its CRL distribution point extension
                CRLVerifier.verifyCertificateCRLs(x509Certificate);
         
                // The chain is built and verified. Return it as a result
                return verifiedCertChain;
            } catch (CertPathBuilderException certPathEx) {
                throw new CertificateVerificationException(
                    "Error building certification path: " + 
                    		x509Certificate.getSubjectX500Principal(), certPathEx);
            } catch (CertificateVerificationException cvex) {
                throw cvex;
            } catch (Exception ex) {
                throw new CertificateVerificationException(
                    "Error verifying the certificate: " + 
                    		x509Certificate.getSubjectX500Principal(), ex);
            }       
        }

	
	 /**
     * Attempts to build a certification chain for given certificate and to verify
     * it. Relies on a set of root CA certificates and intermediate certificates
     * that will be used for building the certification chain. The verification
     * process assumes that all self-signed certificates in the set are trusted
     * root CA certificates and all other certificates in the set are intermediate
     * certificates. 
     * 
     * @param cert - certificate for validation
     * @param additionalCerts - set of trusted root CA certificates that will be
     *      used as "trust anchors" and intermediate CA certificates that will be
     *      used as part of the certification chain. All self-signed certificates
     *      are considered to be trusted root CA certificates. All the rest are
     *      considered to be intermediate CA certificates.
     * @return the certification chain (if verification is successful)
     * @throws CertificateVerificationException - if the certification is not
     *      successful (e.g. certification path cannot be built or some
     *      certificate in the chain is expired or CRL checks are failed)
     */
    public static PKIXCertPathBuilderResult verifyCertificate(X509Certificate cert, 
            Set<X509Certificate> additionalCerts)
            throws CertificateVerificationException {
        try {
            // Check for self-signed certificate
            if (isSelfSigned(cert)) {
                throw new CertificateVerificationException(
                    "The certificate is self-signed.");
            }
             
            // Prepare a set of trusted root CA certificates
            // and a set of intermediate certificates
            Set<X509Certificate> trustedRootCerts = new HashSet<X509Certificate>();
            Set<X509Certificate> intermediateCerts = new HashSet<X509Certificate>();
            for (X509Certificate additionalCert : additionalCerts) {
                if (isSelfSigned(additionalCert)) {
                    trustedRootCerts.add(additionalCert);
                } else {
                    intermediateCerts.add(additionalCert);
                }
            }
             
            // Attempt to build the certification chain and verify it
            PKIXCertPathBuilderResult verifiedCertChain = 
                verifyCertificate(cert, trustedRootCerts, intermediateCerts);
             
            // Check whether the certificate is revoked by the CRL
            // given in its CRL distribution point extension
            CRLVerifier.verifyCertificateCRLs(cert);
     
            // The chain is built and verified. Return it as a result
            return verifiedCertChain;
        } catch (CertPathBuilderException certPathEx) {
            throw new CertificateVerificationException(
                "Error building certification path: " + 
                cert.getSubjectX500Principal(), certPathEx);
        } catch (CertificateVerificationException cvex) {
            throw cvex;
        } catch (Exception ex) {
            throw new CertificateVerificationException(
                "Error verifying the certificate: " + 
                cert.getSubjectX500Principal(), ex);
        }       
    }
     
    /**
     * Checks whether given X.509 certificate is self-signed.
     */
    public static boolean isSelfSigned(X509Certificate cert)
            throws CertificateException, NoSuchAlgorithmException,
            NoSuchProviderException {
        try {
            // Try to verify certificate signature with its own public key
            PublicKey key = cert.getPublicKey();
            cert.verify(key);
            return true;
        } catch (SignatureException sigEx) {
            // Invalid signature --> not self-signed
            return false;
        } catch (InvalidKeyException keyEx) {
            // Invalid key --> not self-signed
            return false;
        }
    }
     
    /**
     * Attempts to build a certification chain for given certificate and to verify
     * it. Relies on a set of root CA certificates (trust anchors) and a set of
     * intermediate certificates (to be used as part of the chain).
     * @param cert - certificate for validation
     * @param trustedRootCerts - set of trusted root CA certificates
     * @param intermediateCerts - set of intermediate certificates
     * @return the certification chain (if verification is successful)
     * @throws GeneralSecurityException - if the verification is not successful
     *      (e.g. certification path cannot be built or some certificate in the
     *      chain is expired)
     */
    private static PKIXCertPathBuilderResult verifyCertificate(X509Certificate cert, Set<X509Certificate> trustedRootCerts,
            Set<X509Certificate> intermediateCerts) throws GeneralSecurityException {
         
        // Create the selector that specifies the starting certificate
        X509CertSelector selector = new X509CertSelector(); 
        selector.setCertificate(cert);
         
        // Create the trust anchors (set of root CA certificates)
        Set<TrustAnchor> trustAnchors = new HashSet<TrustAnchor>();
        for (X509Certificate trustedRootCert : trustedRootCerts) {
            trustAnchors.add(new TrustAnchor(trustedRootCert, null));
        }
         
        // Configure the PKIX certificate builder algorithm parameters
        PKIXBuilderParameters pkixParams = 
            new PKIXBuilderParameters(trustAnchors, selector);
         
        // Disable CRL checks (this is done manually as additional step)
        pkixParams.setRevocationEnabled(false);
     
        // Specify a list of intermediate certificates
        CertStore intermediateCertStore = CertStore.getInstance("Collection",
            new CollectionCertStoreParameters(intermediateCerts), "BC");
        pkixParams.addCertStore(intermediateCertStore);
     
        // Build and verify the certification chain
        CertPathBuilder builder = CertPathBuilder.getInstance("PKIX", "BC");
        PKIXCertPathBuilderResult result = 
            (PKIXCertPathBuilderResult) builder.build(pkixParams);
        return result;
    }
}
