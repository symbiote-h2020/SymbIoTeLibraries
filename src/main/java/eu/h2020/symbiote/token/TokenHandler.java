package eu.h2020.symbiote.token;


import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Token;
import eu.h2020.symbiote.messaging.core.CoreAAMMessageHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class TokenHandler {
	@Autowired CoreAAMMessageHandler coreAAM;

	X509Certificate coreAAMX509Certificate;

	public Token validateCoreToken(String tokenString) throws TokenVerificationException {
		try{
			Token token = new Token(tokenString);
			validateToken(token, getCA());
			return token;
		}catch(CertificateException ex){
			throw new TokenVerificationException("Error validating token", ex);
		}
	}


	public Token validateForeignPlatformToken(String tokenString) {
		//String signature = "?????????????";
		//validate(token, signature);
		return null;
	}

	public Token validateHomePlatformToken(String tokenString) {
		//String signature = "?????????????";
		//validate(token, signature);
		return null;
	}

	private void validateToken(Token token, Certificate certificate) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(certificate.getPublicKey())
	       .parseClaimsJws(token.getToken()).getBody();
	    token.setClaims(claims);
	}
	
    private X509Certificate getCA() throws CertificateException  {
		if (coreAAMX509Certificate==null){
				coreAAMX509Certificate = coreAAM.getCoreAAMRootCertificate();
		}
    	  return coreAAMX509Certificate;
   	}
}
