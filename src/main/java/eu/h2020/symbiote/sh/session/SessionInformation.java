package eu.h2020.symbiote.sh.session;

import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.SHToken;
import eu.h2020.symbiote.sh.messaging.bean.Credential;


public class SessionInformation {
	SHToken homeToken;
	SHToken coreToken;
	Credential usedLoginCredentials;

	public SHToken getHomeToken() {
		return homeToken;
	}

	public void setHomeToken(SHToken homeToken) {
		this.homeToken = homeToken;
	}

	public SHToken getCoreToken() {
		return coreToken;
	}

	public void setCoreToken(SHToken coreToken) {
		this.coreToken = coreToken;
	}
	
}
