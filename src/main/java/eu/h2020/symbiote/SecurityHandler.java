package eu.h2020.symbiote;

import java.security.KeyStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.certificate.CertificateValidator;
import eu.h2020.symbiote.certificate.CertificateVerificationException;
import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import eu.h2020.symbiote.messaging.platform.PlatformAAMMessageHandler;
import eu.h2020.symbiote.session.SessionInformation;
import eu.h2020.symbiote.token.TokenHandler;

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
	@Autowired SessionInformation sessionInformation;
	@Autowired CertificateValidator certificateValidator;
	@Autowired TokenHandler tokenValidator;
	
	public boolean login(String userName, String password){
		Credential credentials = new Credential();
		credentials.setPasswd(userName);
		credentials.setPasswd(password);
		Token homeToken = platformMessageHandler.login(credentials);
		sessionInformation.setHomeToken(homeToken);
		return homeToken!=null;
	}
	
	public void logout(){
		sessionInformation.setHomeToken(null);
	}

	public Token getHomeToken(){
		return sessionInformation.getHomeToken();
	}

	public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException{
		return certificateValidator.validate(p12Certificate);
	}
	
	public void verifyToken(String token){
		Token coreToken = new Token(token);
		tokenValidator.validate(coreToken);	
	}
	
}
