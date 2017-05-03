package eu.h2020.symbiote.security.session;

import eu.h2020.symbiote.security.token.SymbIoTeToken;

import java.util.HashMap;
import java.util.Map;


public class SessionInformation {
    private SymbIoTeToken homeToken;
    private SymbIoTeToken coreToken;
    private Map<String, SymbIoTeToken> foreignTokens = new HashMap<String, SymbIoTeToken>();

    public SessionInformation() {
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

    public Map<String, SymbIoTeToken> getForeignTokens() {
        return foreignTokens;
    }

    public void setForeignTokens(Map<String, SymbIoTeToken> foreignTokens) {
        this.foreignTokens = foreignTokens;
    }

    public SymbIoTeToken getForeignToken(String url) {
        return foreignTokens.get(url);
    }

    public void setForeignToken(String url, SymbIoTeToken token) {
        foreignTokens.put(url, token);
    }

}
