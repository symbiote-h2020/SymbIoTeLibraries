package eu.h2020.symbiote.commons.security.token;


import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import eu.h2020.symbiote.commons.security.messaging.bean.Status;
import eu.h2020.symbiote.commons.security.messaging.bean.Token;
import eu.h2020.symbiote.commons.security.messaging.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.commons.security.messaging.restAAM.AAMMessageHandler;

import eu.h2020.symbiote.commons.security.messaging.platform.foreign.ForeignPlatformAAMMessageHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class TokenHandler {
	private CoreAAMMessageHandler coreAAM;
    private HashMap<String, X509Certificate> publicCertificates; 


    public TokenHandler(CoreAAMMessageHandler coreAAM) {
    	this.coreAAM = coreAAM;
    	this.publicCertificates = new HashMap<String, X509Certificate>();
    }

	public SymbIoTeToken requestCoreToken(SymbIoTeToken homeToken)  {
		return new SymbIoTeToken(coreAAM.requestCoreToken(new Token(homeToken.getToken())));
	}
	
	public SymbIoTeToken requestForeignToken(String aamURL, SymbIoTeToken coreToken)  {
		ForeignPlatformAAMMessageHandler platformAAM = new ForeignPlatformAAMMessageHandler();
		platformAAM.createClient(aamURL);
		return new SymbIoTeToken(platformAAM.requestForeignToken(new Token(coreToken.getToken())));
	}
    
	public void validateCoreToken(SymbIoTeToken token) throws TokenVerificationException {
		try{
			//TODO checkChallengeResponse()
			validateToken(token, getCA(coreAAM));
			checkRevocation(coreAAM, token);
		}catch(CertificateException ex){
			throw new TokenVerificationException("Error validating token", ex);
		}
	}

	public void validateForeignPlatformToken(String aamURL, SymbIoTeToken token) throws TokenVerificationException {
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

	private void validateToken(SymbIoTeToken token, Certificate certificate) {
	    Claims claims = Jwts.parser()         
	       .setSigningKey(certificate.getPublicKey())
	       .parseClaimsJws(token.getToken()).getBody();
	    token.setClaims(claims);
	}

	private void checkRevocation(AAMMessageHandler aamMessagHandler, SymbIoTeToken token) throws TokenVerificationException{
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

