package eu.h2020.symbiote.commons.security.messaging.restAAM;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.h2020.symbiote.commons.security.messaging.bean.Credential;
import eu.h2020.symbiote.commons.security.messaging.bean.Status;
import eu.h2020.symbiote.commons.security.messaging.bean.Token;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public abstract class AAMMessageHandler {
	private static final Log logger = LogFactory.getLog(AAMMessageHandler.class);
	private AAMRestService simpleclient;
	private AAMRestService jsonclient;
	private String url;

    public AAMMessageHandler() {
    }
	
	
	public void createClient(String url) {
		this.url = url;
		simpleclient = Feign.builder().target(AAMRestService.class, url);
		jsonclient = Feign.builder().decoder(new JacksonDecoder()).encoder(new JacksonEncoder()).target(AAMRestService.class, url);
	}

	public String getURL(){
		return url;
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
	
	public Token login(Credential credential)  {
		Token result = null;
		try{
            logger.info("User trying to login "+credential.getUser()+ " - "+credential.getPasswd());
			result = jsonclient.login(credential);
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}

	public Status checkTokenRevocation(Token token)  {
		Status result = null;
		try{
            logger.info("User trying to checkTokenRevocation");
			result = jsonclient.checkTokenRevocation(token);
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}

	public Token requestCoreToken(Token token)  {
		Token result = null;
		try{
            logger.info("User trying to requestCoreToken");
			result = jsonclient.requestCoreToken(token);
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}

	public Token requestForeignToken(Token token)  {
		Token result = null;
		try{
            logger.info("User trying to requestForeignToken");
			result = jsonclient.requestForeignToken(token);
		}catch(Throwable t){
			logger.error("Error accessing to AAM server at "+url, t);
		}
		return result;
	}

}

