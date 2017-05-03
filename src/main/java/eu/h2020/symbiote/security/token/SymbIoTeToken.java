package eu.h2020.symbiote.security.token;

import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.payloads.Token;
import io.jsonwebtoken.Claims;

public class SymbIoTeToken {
    public final static String JWT_CLAIMS_AUDIENCE = Claims.AUDIENCE;
    public final static String JWT_CLAIMS_ID = Claims.ID;
    public final static String JWT_CLAIMS_EXPIRATION = Claims.EXPIRATION;
    public final static String JWT_CLAIMS_ISSUED_AT = Claims.ISSUED_AT;
    public final static String JWT_CLAIMS_ISSUER = Claims.ISSUER;
    public final static String JWT_CLAIMS_NOT_BEFORE = Claims.NOT_BEFORE;
    public final static String JWT_CLAIMS_SUBJECT = Claims.SUBJECT;
    public final static String JWT_CLAIMS_TTYPE = AAMConstants.CLAIM_NAME_TOKEN_TYPE;

    private String token; //encrypted
    private Claims claims;

    public SymbIoTeToken(Token token) {
        this.token = token.getToken();
    }

    public SymbIoTeToken(String token) {
        this.token = token;
    }

    public SymbIoTeToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public Object getClaim(String claimName) {
        return claims.get(claimName);
    }

    public Claims getAllClaims() {
        return this.claims;
    }
}
