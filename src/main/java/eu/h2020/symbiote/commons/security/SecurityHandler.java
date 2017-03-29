package eu.h2020.symbiote.commons.security;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

import eu.h2020.symbiote.commons.security.messaging.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.commons.security.token.SymbIoTeToken;
import eu.h2020.symbiote.commons.security.token.TokenVerificationException;

import eu.h2020.symbiote.commons.security.certificate.CertificateValidator;
import eu.h2020.symbiote.commons.security.certificate.CertificateVerificationException;
import eu.h2020.symbiote.commons.security.messaging.bean.Credential;
import eu.h2020.symbiote.commons.security.messaging.platform.home.PlatformAAMMessageHandler;
import eu.h2020.symbiote.commons.security.session.SessionInformation;
import eu.h2020.symbiote.commons.security.token.TokenHandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class exposing the library from security handler
 *
 * @author: Elena Garrido
 * @version: 08/03/2017
 */
/**! \class SecurityHandler
 * \brief This class implement the methods to be used by the component in order to integrate with the security from symbIoTe
 **/

public class SecurityHandler {
	private static final Log logger = LogFactory.getLog(SecurityHandler .class);
	private PlatformAAMMessageHandler platformMessageHandler;
	private CoreAAMMessageHandler coreMessageHandler;
	private SessionInformation sessionInformation;
	private TokenHandler tokenHandler;
	private CertificateValidator certificateValidator; 
	
    public  SecurityHandler(String coreAAMUrl, String rabbitMQHostIP) {
    	this.platformMessageHandler = new PlatformAAMMessageHandler(rabbitMQHostIP);
    	this.coreMessageHandler = new CoreAAMMessageHandler(coreAAMUrl);
    	this.sessionInformation = new SessionInformation();
    	this.tokenHandler = new TokenHandler(this.coreMessageHandler);
    	this.certificateValidator = new CertificateValidator(this.coreMessageHandler);
    }
	

	public SymbIoTeToken appRequestCoreToken(String userName, String password){
		SymbIoTeToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken==null){
			//not logged in
			Credential credentials = new Credential();
			credentials.setUser(userName);
			credentials.setPasswd(password);
			coreToken= new SymbIoTeToken(coreMessageHandler.login(credentials));
			sessionInformation.setCoreToken(coreToken);
		}
		return coreToken;		
	}

	public SymbIoTeToken requestCoreToken(String userName, String password){
		SymbIoTeToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken==null){
			//not logged in
			Credential credentials = new Credential();
			credentials.setUser(userName);
			credentials.setPasswd(password);
			SymbIoTeToken homeToken= new SymbIoTeToken(platformMessageHandler.login(credentials));
			//TODO challenge response procedure??
			coreToken = tokenHandler.requestCoreToken(homeToken);
			sessionInformation.setHomeToken(homeToken);
			sessionInformation.setCoreToken(coreToken);
		}
		return coreToken;		
	}

	
	
	public HashMap<String, SymbIoTeToken> requestForeignTokens(List<String> aamUrls){
		HashMap<String, SymbIoTeToken> foreignTokens = null;
		SymbIoTeToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken!=null){
			//logged in
			foreignTokens = new HashMap<String, SymbIoTeToken>(); 
			for (String url : aamUrls){
				SymbIoTeToken foreignToken = sessionInformation.getForeignToken(url);
				if (foreignToken==null){
					foreignToken = tokenHandler.requestForeignToken(url, coreToken);
					sessionInformation.setForeignToken(url, foreignToken);
				}
				foreignTokens.put(url, foreignToken);
			}
		}
		return foreignTokens;		
	}

	public void logout(){
		sessionInformation.setHomeToken(null);
		sessionInformation.setCoreToken(null);		
	}
	
	public SymbIoTeToken getHomeToken(){
		return sessionInformation.getHomeToken();
	}

	public SymbIoTeToken getCoreToken(){
		return sessionInformation.getCoreToken();
	}

	public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException{
		return certificateValidator.validate(p12Certificate);
	}


	public SymbIoTeToken verifyCoreToken(String encodedTokenString) throws TokenVerificationException{
		SymbIoTeToken token = new SymbIoTeToken(encodedTokenString);
		verifyCoreToken(token);
		return token;
	}

	public void verifyCoreToken(SymbIoTeToken token) throws TokenVerificationException{
		tokenHandler.validateCoreToken(token);
	}

	public SymbIoTeToken verifyForeignPlatformToken(String aamURL, String encodedTokenString) throws TokenVerificationException{
		SymbIoTeToken token = new SymbIoTeToken(encodedTokenString);
		verifyForeignPlatformToken(aamURL, token);
		return token;
	}

	public void verifyForeignPlatformToken(String aamURL, SymbIoTeToken token) throws TokenVerificationException{
		tokenHandler.validateForeignPlatformToken(aamURL, token);
	}

}