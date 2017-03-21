package eu.h2020.symbiote.token;


import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class TokenHandler {
	

	public void validate(Token token) {
	String signature = "?????????????";
	validate(token, signature);
}

	private void validate(Token token, String signature) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(signature.getBytes())
	       .parseClaimsJws(token.getToken()).getBody();
	    token.setClaims(claims);
	}
	
}
