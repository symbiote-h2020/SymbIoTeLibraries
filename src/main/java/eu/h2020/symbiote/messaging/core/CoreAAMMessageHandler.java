package eu.h2020.symbiote.messaging.core;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import feign.Feign;

@Component
@EnableAutoConfiguration
public class CoreAAMMessageHandler {
	@Value("${symbiote.coreaam.uri}")
	String coreAAMUri;
	static CoreAAMRestService client;
	  private static final Log logger = LogFactory.getLog(CoreAAMMessageHandler.class);

	public CoreAAMMessageHandler(){
	}
	@PostConstruct
	public void print() {
		System.out.println("coreAAMUri "+coreAAMUri);
		client = Feign.builder().target(CoreAAMRestService.class, coreAAMUri);
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
