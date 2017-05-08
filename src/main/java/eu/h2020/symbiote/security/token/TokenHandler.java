package eu.h2020.symbiote.security.token;


import eu.h2020.symbiote.security.amqp.core.CoreAAMMessageHandler;
import eu.h2020.symbiote.security.amqp.platform.foreign.ForeignPlatformAAMMessageHandler;
import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import eu.h2020.symbiote.security.exceptions.aam.TokenValidationException;
import eu.h2020.symbiote.security.rest.AAMMessageHandler;
import io.jsonwebtoken.*;

import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

public class TokenHandler {
    private CoreAAMMessageHandler coreAAM;
    private HashMap<String, X509Certificate> publicCertificates;


    public TokenHandler(CoreAAMMessageHandler coreAAM) {
        this.coreAAM = coreAAM;
        this.publicCertificates = new HashMap<String, X509Certificate>();
    }

    public Token requestCoreToken(Token homeToken) {
        return coreAAM.requestCoreToken(homeToken);
    }

    public Token requestForeignToken(String aamURL, Token coreToken) {
        ForeignPlatformAAMMessageHandler platformAAM = new ForeignPlatformAAMMessageHandler();
        platformAAM.createClient(aamURL);
        return platformAAM.requestForeignToken(coreToken);
    }

    public void validateCoreToken(Token token) throws TokenValidationException {
        try {
            //TODO checkChallengeResponse()
            validateToken(token, getCA(coreAAM));
            checkRevocation(coreAAM, token);
        } catch (CertificateException ex) {
            throw new TokenValidationException("Error validating token", ex);
        }
    }

    public void validateForeignPlatformToken(String aamURL, Token token) throws TokenValidationException {
        try {
            ForeignPlatformAAMMessageHandler platformAAM = new ForeignPlatformAAMMessageHandler();
            platformAAM.createClient(aamURL);
            //TODO checkChallengeResponse()
            validateToken(token, getCA(platformAAM));
            checkRevocation(platformAAM, token);
        } catch (CertificateException ex) {
            throw new TokenValidationException("Error validating token", ex);
        }
    }

    private void validateToken(Token token, Certificate certificate) throws TokenValidationException {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(certificate.getPublicKey())
                    .parseClaimsJws(token.getToken()).getBody();
            token.setClaims(claims);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new TokenValidationException("Token could not be validated", e);
        }
    }

    private void checkRevocation(AAMMessageHandler aamMessageHandler, Token tokenForRevocation) throws TokenValidationException {
        TokenValidationStatus status = aamMessageHandler.checkTokenRevocation(tokenForRevocation);
        if (status == null) {
            throw new TokenValidationException("Error retrieving the status revocation of the token");
        }
        if (status == TokenValidationStatus.REVOKED) {
            throw new TokenValidationException("Token has been revoked");
        }
    }


    private X509Certificate getCA(AAMMessageHandler aamMessagHandler) throws CertificateException {
        String url = aamMessagHandler.getURL();
        X509Certificate aamX509Certificate = publicCertificates.get(url);
        if (aamX509Certificate == null) {
            aamX509Certificate = aamMessagHandler.getAAMRootCertificate();
            publicCertificates.put(url, aamX509Certificate);
        }
        return aamX509Certificate;
    }

}

