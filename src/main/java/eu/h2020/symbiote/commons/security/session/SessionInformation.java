package eu.h2020.symbiote.commons.security.session;

import java.util.HashMap;

import eu.h2020.symbiote.commons.security.token.SymbIoTeToken;


public class SessionInformation {
	private SymbIoTeToken homeToken;
	private SymbIoTeToken coreToken;
	private HashMap<String, SymbIoTeToken> foreignTokens; 

    public SessionInformation() {
    	this.foreignTokens = new HashMap<String, SymbIoTeToken>() ; 

    }

	public SymbIoTeToken getHomeToken() {
		return homeToken;
	}

	public void setHomeToken(SymbIoTeToken homeToken) {
		this.homeToken = homeToken;
	}

	public SymbIoTeToken getCoreToken() {
		return coreToken;
	}

	public void setCoreToken(SymbIoTeToken coreToken) {
		this.coreToken = coreToken;
	}

	public HashMap<String, SymbIoTeToken> getForeignTokens() {
		return foreignTokens;
	}

	public void setForeignTokens(HashMap<String, SymbIoTeToken> foreignTokens) {
		this.foreignTokens = foreignTokens;
	}
	
	public SymbIoTeToken getForeignToken(String url){
		return foreignTokens.get(url);
	}

	public void setForeignToken(String url, SymbIoTeToken token){
		foreignTokens.put(url, token);
	}
	
}
