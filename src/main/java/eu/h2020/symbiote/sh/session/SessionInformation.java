package eu.h2020.symbiote.sh.session;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.SHToken;


public class SessionInformation {
	private SHToken homeToken;
	private SHToken coreToken;
	private HashMap<String, SHToken> foreignTokens; 

    public SessionInformation() {
    	this.foreignTokens = new HashMap<String, SHToken>() ; 

    }

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

	public HashMap<String, SHToken> getForeignTokens() {
		return foreignTokens;
	}

	public void setForeignTokens(HashMap<String, SHToken> foreignTokens) {
		this.foreignTokens = foreignTokens;
	}
	
	public SHToken getForeignToken(String url){
		return foreignTokens.get(url);
	}

	public void setForeignToken(String url, SHToken token){
		foreignTokens.put(url, token);
	}
	
}
