package eu.h2020.symbiote.messaging.restAAM;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import feign.Feign;

public class AAMMessageHandler {
	private static final Log logger = LogFactory.getLog(AAMMessageHandler.class);
	AAMRestService client;

	public AAMMessageHandler(){
	}

	public void createClient(String url) {
		client = Feign.builder().target(AAMRestService.class, url);
	}

	public byte[] getCoreAAMRootCertificateAsByteArray(){
		byte[]result = null;
		try{
			result = client.getRootCertificate();
		}catch(Throwable t){
			logger.error("Error accessing to coreAAM server", t);
		}
		return result;
	}
	
	public  X509Certificate getCoreAAMRootCertificate() throws CertificateException {
		byte[]keyBytes = getCoreAAMRootCertificateAsByteArray();
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		if (keyBytes!=null)
			return (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(keyBytes));
		return null;
	 }
}
