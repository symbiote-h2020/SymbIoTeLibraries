package eu.h2020.symbiote.session;

import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;

@Component
public class SessionInformation {
	Token platformToken;
	Token coreToken;
	Credential usedLoginCredentials;

	public Token getPlatformToken() {
		return platformToken;
	}

	public void setPlatformToken(Token platformToken) {
		this.platformToken = platformToken;
	}

	public Credential getUsedLoginCredentials() {
		return usedLoginCredentials;
	}

	public void setUsedLoginCredentials(Credential usedLoginCredentials) {
		this.usedLoginCredentials = usedLoginCredentials;
	}

	public Token getCoreToken() {
		return coreToken;
	}

	public void setCoreToken(Token coreToken) {
		this.coreToken = coreToken;
	}
	
}
