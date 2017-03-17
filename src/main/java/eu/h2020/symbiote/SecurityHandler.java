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
	
	
	public boolean login(String userName, String password){
		Credential credentials = new Credential();
		credentials.setPasswd(userName);
		credentials.setPasswd(password);
		Token token = platformMessageHandler.login(credentials);
		sessionInformation.setCoreToken(token);
		return token!=null;
	}
	
	public void logout(){
		sessionInformation.setCoreToken(null);
	}

	public void getPlatformToken(){
		sessionInformation.getPlatformToken();
	}

	public boolean certificateValidation(KeyStore p12Certificate) throws CertificateVerificationException{
		return certificateValidator.validate(p12Certificate);
	}
	
}
