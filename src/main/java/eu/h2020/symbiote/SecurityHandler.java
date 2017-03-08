package eu.h2020.symbiote;

import org.springframework.beans.factory.annotation.Autowired;

import eu.h2020.symbiote.certificate.CertificateValidator;
import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import eu.h2020.symbiote.messaging.core.CoreMessageHandler;
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
public class SecurityHandler {
	@Autowired CoreMessageHandler coreMessageHandler;
	@Autowired SessionInformation sessionInformation;
	@Autowired CertificateValidator certificateValidator;
	
	
	boolean login(String userName, String password){
		Credential credentials = new Credential();
		credentials.setPasswd(userName);
		credentials.setPasswd(password);
		Token token = coreMessageHandler.login(credentials);
		sessionInformation.setCoreToken(token);
		return token!=null;
	}
	
	void logout(){
		sessionInformation.setCoreToken(null);
	}
	
	boolean certificateValidation(byte []certificate){
		return certificateValidator.validate(certificate);
	}
	
}
