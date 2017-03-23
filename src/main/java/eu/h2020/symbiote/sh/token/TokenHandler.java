package eu.h2020.symbiote.sh.token;


import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.SHToken;
import eu.h2020.symbiote.sh.messaging.bean.Status;
import eu.h2020.symbiote.sh.messaging.bean.Token;
import eu.h2020.symbiote.sh.messaging.core.CoreAAMMessageHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenHandler {
	CoreAAMMessageHandler coreAAM;

	X509Certificate coreAAMX509Certificate;

    public TokenHandler(CoreAAMMessageHandler coreAAM) {
    	this.coreAAM = coreAAM;
    }
    
	public void validateCoreToken(SHToken token) throws TokenVerificationException {
		try{
			//TODO checkChallengeResponse()
			validateToken(token, getCA());
			checkRevocation(token);
		}catch(CertificateException ex){
			throw new TokenVerificationException("Error validating token", ex);
		}
	}

	public void validateForeignPlatformToken(String aamURL, SHToken token) throws TokenVerificationException {
		throw new TokenVerificationException("TO BE IMPLEMENTED");
	}

	private void validateToken(SHToken token, Certificate certificate) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(certificate.getPublicKey())
	       .parseClaimsJws(token.getToken()).getBody();
	    token.setClaims(claims);
	}

	private void checkRevocation(SHToken token) throws TokenVerificationException{
		Token tokeForRevocation = new Token();
		tokeForRevocation.setToken(token.getToken());
		Status status = coreAAM.checkTokenRevocation(tokeForRevocation);
		if (status==null){
			throw new TokenVerificationException("Error retrieving the status revocation of the token");
		}
		if (!Status.SUCCESS.equals(status.getStatus())){
			throw new TokenVerificationException("Token has been revoked");			
		}
	}

    private X509Certificate getCA() throws CertificateException  {
		if (coreAAMX509Certificate==null)
				coreAAMX509Certificate = coreAAM.getAAMRootCertificate();
		return coreAAMX509Certificate;
   	}


}
