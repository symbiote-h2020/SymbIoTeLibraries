package eu.h2020.symbiote.messaging.core;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import feign.Feign;

@Service
public class CoreAAMMessageHandler {
	@Value("${symbiote.coreaam.uri}")
	String coreAAMUri;
	CoreAAMRestService client;

	public CoreAAMMessageHandler(){
		
	}
	
	public byte[] getCoreAAMRootCertificateAsByteArray(){
		coreAAMUri = "http://localhost:8080";
		client = Feign.builder().target(CoreAAMRestService.class, coreAAMUri);
		return client.getRootCertificate();
	}
	
	public  X509Certificate getCoreAAMRootCertificate() throws CertificateException {
		byte[]keyBytes = getCoreAAMRootCertificateAsByteArray();
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		return (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(keyBytes));
	 }
}
