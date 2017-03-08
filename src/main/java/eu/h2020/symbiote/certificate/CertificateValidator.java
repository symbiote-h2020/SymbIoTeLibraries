package eu.h2020.symbiote.certificate;

public class CertificateValidator {

	public boolean validate(byte[] certificate) {
		/*TBD	TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init((KeyStore)null);
		

		for (TrustManager trustManager: trustManagerFactory.getTrustManagers()) {  
		    if (trustManager instanceof X509TrustManager) {  
		        X509TrustManager x509TrustManager = (X509TrustManager)trustManager;  
		        x509TrustManager.checkServerTrusted(...);
		    }  
		}*/		return false;
	}

}
