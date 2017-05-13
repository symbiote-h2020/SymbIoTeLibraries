package eu.h2020.symbiote.security.session;

import eu.h2020.symbiote.security.token.Token;

import java.util.HashMap;
import java.util.Map;

// TODO rework
public class SessionInformation {
    // TODO rework to support multiple Platform tokens
    private Token homeToken;
    // todo rework to have a wallet of tokens grouped by issuing AAMs
    private Token coreToken;
    // todo rename to federated tokens
    private Map<String, Token> foreignTokens = new HashMap<>();

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
