package eu.h2020.symbiote.sh.token;


import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.sh.SHToken;
import eu.h2020.symbiote.sh.messaging.bean.Status;
import eu.h2020.symbiote.sh.messaging.bean.Token;
import eu.h2020.symbiote.sh.messaging.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.sh.messaging.platform.foreign.ForeignPlatformAAMMessageHandler;
import eu.h2020.symbiote.sh.messaging.restAAM.AAMMessageHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenHandler {
	private CoreAAMMessageHandler coreAAM;
    private HashMap<String, X509Certificate> publicCertificates; 


    public TokenHandler(CoreAAMMessageHandler coreAAM) {
    	this.coreAAM = coreAAM;
    	this.publicCertificates = new HashMap<String, X509Certificate>();
    }

	public SHToken requestCoreToken(SHToken homeToken)  {
		return new SHToken(coreAAM.requestCoreToken(new Token(homeToken.getToken())));
	}
	
	public SHToken requestForeignToken(String aamURL, SHToken coreToken)  {
		ForeignPlatformAAMMessageHandler platformAAM = new ForeignPlatformAAMMessageHandler();
		platformAAM.createClient(aamURL);
		return new SHToken(platformAAM.requestForeignToken(new Token(coreToken.getToken())));
	}
    
	public void validateCoreToken(SHToken token) throws TokenVerificationException {
		try{
			//TODO checkChallengeResponse()
			validateToken(token, getCA(coreAAM));
			checkRevocation(coreAAM, token);
		}catch(CertificateException ex){
			throw new TokenVerificationException("Error validating token", ex);
		}
	}

	public void validateForeignPlatformToken(String aamURL, SHToken token) throws TokenVerificationException {
		try{
			ForeignPlatformAAMMessageHandler platformAAM = new ForeignPlatformAAMMessageHandler();
			platformAAM.createClient(aamURL);
			//TODO checkChallengeResponse()
			validateToken(token, getCA(platformAAM));
			checkRevocation(platformAAM, token);
		}catch(CertificateException ex){
			throw new TokenVerificationException("Error validating token", ex);
		}
	}

	private void validateToken(SHToken token, Certificate certificate) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(certificate.getPublicKey())
	       .parseClaimsJws(token.getToken()).getBody();
	    token.setClaims(claims);
	}

	private void checkRevocation(AAMMessageHandler aamMessagHandler, SHToken token) throws TokenVerificationException{
		Token tokeForRevocation = new Token();
		tokeForRevocation.setToken(token.getToken());
		Status status = aamMessagHandler.checkTokenRevocation(tokeForRevocation);
		if (status==null){
			throw new TokenVerificationException("Error retrieving the status revocation of the token");
		}
		if (!Status.SUCCESS.equals(status.getStatus())){
			throw new TokenVerificationException("Token has been revoked");			
		}
	}


    private X509Certificate getCA(AAMMessageHandler aamMessagHandler) throws CertificateException  {
    	String url = aamMessagHandler.getURL();
    	X509Certificate aamX509Certificate = publicCertificates.get(url); 	
    	if (aamX509Certificate==null){
    		aamX509Certificate = aamMessagHandler.getAAMRootCertificate();
    		publicCertificates.put(url, aamX509Certificate);
    	}
		return aamX509Certificate;
   	}

}
