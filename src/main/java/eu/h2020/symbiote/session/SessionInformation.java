package eu.h2020.symbiote.session;

import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;

@Component
public class SessionInformation {
	Token coreToken;
	Credential usedLoginCredentials;

	public Token getCoreToken() {
		return coreToken;
	}

	public void setCoreToken(Token coreToken) {
		this.coreToken = coreToken;
	}

	public Credential getUsedLoginCredentials() {
		return usedLoginCredentials;
	}

	public void setUsedLoginCredentials(Credential usedLoginCredentials) {
		this.usedLoginCredentials = usedLoginCredentials;
	}
	
	
}
