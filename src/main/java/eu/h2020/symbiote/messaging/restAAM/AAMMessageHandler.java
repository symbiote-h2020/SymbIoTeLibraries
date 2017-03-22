package eu.h2020.symbiote.messaging.restAAM;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class AAMMessageHandler {
	private static final Log logger = LogFactory.getLog(AAMMessageHandler.class);
	AAMRestService simpleclient;
	AAMRestService jsonclient;
	String url;

	public void createClient(String url) {
		this.url = url;
		simpleclient = Feign.builder().target(AAMRestService.class, url);
		jsonclient = Feign.builder().decoder(new GsonDecoder()).encoder(new GsonEncoder()).target(AAMRestService.class, url);
	}

	private byte[] getAAMRootCertificateAsByteArray(){
		byte[]result = null;
		try{
			result = simpleclient.getRootCertificate();
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}
	
	public  X509Certificate getAAMRootCertificate() throws CertificateException {
		byte[]keyBytes = getAAMRootCertificateAsByteArray();
		CertificateFactory fact = CertificateFactory.getInstance("X.509");
		if (keyBytes!=null)
			return (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(keyBytes));
		return null;
	 }
	
	public  Token login(Credential credential)  {
		Token result = null;
		try{
            logger.info("User trying to login "+credential.getUser()+ " - "+credential.getPasswd());
			result = jsonclient.login(credential);
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}

}
