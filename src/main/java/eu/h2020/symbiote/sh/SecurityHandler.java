package eu.h2020.symbiote.sh;

import java.security.KeyStore;

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
	PlatformAAMMessageHandler platformMessageHandler;
	CoreAAMMessageHandler coreMessageHandler;
	SessionInformation sessionInformation;
	TokenHandler tokenHandler;
	CertificateValidator certificateValidator; 
	
    public  SecurityHandler(String coreAAMUrl, String rabbitMQHostIP) {
    	this.platformMessageHandler = new PlatformAAMMessageHandler(rabbitMQHostIP);
    	this.coreMessageHandler = new CoreAAMMessageHandler(coreAAMUrl);
    	this.sessionInformation = new SessionInformation();
    	this.tokenHandler = new TokenHandler(this.coreMessageHandler);
    	this.certificateValidator = new CertificateValidator(this.coreMessageHandler);
    }

	public boolean homeLogin(String userName, String password){
		Credential credentials = new Credential();
		credentials.setUser(userName);
		credentials.setPasswd(password);
		SHToken homeToken= new SHToken(platformMessageHandler.login(credentials));
		sessionInformation.setHomeToken(homeToken);
		return homeToken!=null;
	}

	public boolean coreLogin(String userName, String password){
		Credential credentials = new Credential();
		credentials.setUser(userName);
		credentials.setPasswd(password);
		SHToken coreToken= new SHToken(coreMessageHandler.login(credentials));
		sessionInformation.setCoreToken(coreToken);
		return coreToken!=null;		
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
