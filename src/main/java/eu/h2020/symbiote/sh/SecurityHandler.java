package eu.h2020.symbiote.sh;

import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.certificate.CertificateValidator;
import eu.h2020.symbiote.sh.certificate.CertificateVerificationException;
import eu.h2020.symbiote.sh.messaging.bean.Credential;
import eu.h2020.symbiote.sh.messaging.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.sh.messaging.platform.home.PlatformAAMMessageHandler;
import eu.h2020.symbiote.sh.session.SessionInformation;
import eu.h2020.symbiote.sh.token.TokenHandler;
import eu.h2020.symbiote.sh.token.TokenVerificationException;

/**
 * Class exposing the library from security handler
 *
 * @author: Elena Garrido
 * @version: 08/03/2017

 */
/**! \class SecurityHandler
 * \brief This class implement the methods to be used by the component in order to integrate with the security from symbIoTe
 **/
@Component
public class SecurityHandler {
	@Autowired PlatformAAMMessageHandler platformMessageHandler;
	@Autowired CoreAAMMessageHandler coreMessageHandler;
	@Autowired SessionInformation sessionInformation;
	@Autowired TokenHandler tokenHandler;
	@Autowired CertificateValidator certificateValidator; 
	

	public SHToken appRequestCoreToken(String userName, String password){
		SHToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken==null){
			//not logged in
			Credential credentials = new Credential();
			credentials.setUser(userName);
			credentials.setPasswd(password);
			coreToken= new SHToken(coreMessageHandler.login(credentials));
			sessionInformation.setCoreToken(coreToken);
		}
		return coreToken;		
	}

	public SHToken requestCoreToken(String userName, String password){
		SHToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken==null){
			//not logged in
			Credential credentials = new Credential();
			credentials.setUser(userName);
			credentials.setPasswd(password);
			SHToken homeToken= new SHToken(platformMessageHandler.login(credentials));
			//TODO challenge response procedure??
			coreToken = tokenHandler.requestCoreToken(homeToken);
			sessionInformation.setHomeToken(homeToken);
			sessionInformation.setCoreToken(coreToken);
		}
		return coreToken;		
	}

	
	
	public HashMap<String, SHToken> requestForeignTokens(List<String> aamUrls){
		HashMap<String, SHToken> foreignTokens = null;
		SHToken coreToken = sessionInformation.getCoreToken(); 
		if (coreToken!=null){
			//logged in
			foreignTokens = new HashMap<String, SHToken>(); 
			for (String url : aamUrls){
				SHToken foreignToken = sessionInformation.getForeignToken(url);
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
	
	public SHToken getHomeToken(){
		return sessionInformation.getHomeToken();
	}

	public SHToken getCoreToken(){
		return sessionInformation.getCoreToken();
	}

	public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException{
		return certificateValidator.validate(p12Certificate);
	}


	public SHToken verifyCoreToken(String encodedTokenString) throws TokenVerificationException{
		SHToken token = new SHToken(encodedTokenString);
		verifyCoreToken(token);
		return token;
	}

	public void verifyCoreToken(SHToken token) throws TokenVerificationException{
		tokenHandler.validateCoreToken(token);
	}

	public SHToken verifyForeignPlatformToken(String aamURL, String encodedTokenString) throws TokenVerificationException{
		SHToken token = new SHToken(encodedTokenString);
		verifyForeignPlatformToken(aamURL, token);
		return token;
	}

	public void verifyForeignPlatformToken(String aamURL, SHToken token) throws TokenVerificationException{
		tokenHandler.validateForeignPlatformToken(aamURL, token);
	}

}
