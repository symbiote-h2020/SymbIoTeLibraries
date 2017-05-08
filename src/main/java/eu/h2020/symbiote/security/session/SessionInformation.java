package eu.h2020.symbiote.security.session;

import eu.h2020.symbiote.security.token.Token;

import java.util.HashMap;
import java.util.Map;


public class SessionInformation {
    private Token homeToken;
    private Token coreToken;
    private Map<String, Token> foreignTokens = new HashMap<String, Token>();

    public SessionInformation() {
    }

    public Token getHomeToken() {
        return homeToken;
    }

    public void setHomeToken(Token homeToken) {
        this.homeToken = homeToken;
    }

    public Token getCoreToken() {
        return coreToken;
    }

    public void setCoreToken(Token coreToken) {
        this.coreToken = coreToken;
    }

    public Map<String, Token> getForeignTokens() {
        return foreignTokens;
    }

    public void setForeignTokens(Map<String, Token> foreignTokens) {
        this.foreignTokens = foreignTokens;
    }

    public Token getForeignToken(String url) {
        return foreignTokens.get(url);
    }

    public void setForeignToken(String url, Token token) {
        foreignTokens.put(url, token);
    }

}
