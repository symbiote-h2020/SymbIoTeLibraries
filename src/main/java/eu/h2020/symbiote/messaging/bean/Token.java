package eu.h2020.symbiote.messaging.bean;

import io.jsonwebtoken.Claims;

public class Token {
	String token; //encrypted
	Claims claims;
	public final static String JWT_CLAIMS_AUDIENCE = Claims.AUDIENCE;
	public final static String JWT_CLAIMS_ID = Claims.ID;
	public final static String JWT_CLAIMS_EXPIRATION = Claims.EXPIRATION;
	public final static String JWT_CLAIMS_ISSUED_AT = Claims.ISSUED_AT;
	public final static String JWT_CLAIMS_ISSUER = Claims.ISSUER;
	public final static String JWT_CLAIMS_NOT_BEFORE = Claims.NOT_BEFORE;
	public final static String JWT_CLAIMS_SUBJECT = Claims.SUBJECT;
	
	public Token(String token){
		this.token = token;
	}
	
	public Token(){
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setClaims(Claims claims){
		this.claims = claims;
	}
	
	Object getClaim(String claimName){
		return claims.get(claimName);
	}
}
