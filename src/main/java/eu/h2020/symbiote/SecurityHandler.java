package eu.h2020.symbiote;

import java.security.KeyStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.certificate.CertificateValidator;
import eu.h2020.symbiote.certificate.CertificateVerificationException;
import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import eu.h2020.symbiote.messaging.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.messaging.platform.home.PlatformAAMMessageHandler;
import eu.h2020.symbiote.session.SessionInformation;
import eu.h2020.symbiote.token.TokenHandler;
import eu.h2020.symbiote.token.TokenVerificationException;

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
	@Autowired TokenHandler tokenValidator;
	@Autowired CertificateValidator certificateValidator; 
	
	public boolean homeLogin(String userName, String password){
		Credential credentials = new Credential();
		credentials.setUser(userName);
		credentials.setPasswd(password);
		Token homeToken = platformMessageHandler.login(credentials);
		sessionInformation.setHomeToken(homeToken);
		return homeToken!=null;
	}

	public boolean coreLogin(String userName, String password){
		Credential credentials = new Credential();
		credentials.setUser(userName);
		credentials.setPasswd(password);
		Token coreToken = coreMessageHandler.login(credentials);
		sessionInformation.setCoreToken(coreToken);
		return coreToken!=null;		
	}

	public void logout(){
		sessionInformation.setHomeToken(null);
		sessionInformation.setCoreToken(null);		
	}
	
	public Token getHomeToken(){
		return sessionInformation.getHomeToken();
	}

	public Token getCoreToken(){
		return sessionInformation.getCoreToken();
	}

	public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException{
		return certificateValidator.validate(p12Certificate);
	}


	public Token verifyCoreToken(String encodedTokenString) throws TokenVerificationException{
		Token token = new Token(encodedTokenString);
		tokenValidator.validateCoreToken(token);
		return token;
	}

	public void verifyCoreToken(Token token) throws TokenVerificationException{
		tokenValidator.validateCoreToken(token);
	}

	public Token verifyForeignPlatformToken(String aamURL, String encodedTokenString) throws TokenVerificationException{
		Token token = new Token(encodedTokenString);
		tokenValidator.validateForeignPlatformToken(aamURL ,token);
		return token;
	}

	public void verifyForeignPlatformToken(String aamURL, Token token) throws TokenVerificationException{
		tokenValidator.validateForeignPlatformToken(aamURL, token);
	}

}
