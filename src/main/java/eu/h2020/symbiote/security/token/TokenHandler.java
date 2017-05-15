package eu.h2020.symbiote.security.token;


import eu.h2020.symbiote.security.enums.ValidationStatus;
import eu.h2020.symbiote.security.rest.clients.AAMClient;
import eu.h2020.symbiote.security.rest.clients.CoreAAMClient;
import eu.h2020.symbiote.security.session.AAM;
import io.jsonwebtoken.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

public class TokenHandler {
    private static Log log = LogFactory.getLog(TokenHandler.class);
    private CoreAAMClient coreAAM;
    private HashMap<String, X509Certificate> publicCertificates;


    public TokenHandler(CoreAAMClient coreAAM) {
        this.coreAAM = coreAAM;
        this.publicCertificates = new HashMap<>();
    }

    public Token requestCoreToken(Token homeToken) {
        return coreAAM.requestFederatedCoreToken(homeToken);
    }

    public Token requestForeignToken(AAM aam, Token coreToken) {
        AAMClient platformAAM = new AAMClient(aam);
        return platformAAM.requestForeignToken(coreToken);
    }

    public ValidationStatus validateCoreToken(Token token) {
        try {
            ValidationStatus status;
            //TODO checkChallengeResponse()
            status = validateToken(token, getCA(coreAAM));
            if (status != ValidationStatus.VALID)
                return status;
            return checkRevocation(coreAAM, token);
        } catch (CertificateException ex) {
            log.error(ex);
            return ValidationStatus.INVALID;
        }
    }

    public ValidationStatus validateForeignPlatformToken(AAM aam, Token token) {
        try {
            AAMClient platformAAM = new AAMClient(aam);
            ValidationStatus status;
            //TODO checkChallengeResponse()
            status = validateToken(token, getCA(platformAAM));
            if (status != ValidationStatus.VALID)
                return status;
            return checkRevocation(platformAAM, token);
        } catch (CertificateException ex) {
            log.error(ex);
            return ValidationStatus.INVALID;
        }
    }

    private ValidationStatus validateToken(Token token, Certificate certificate) {
        try {
            // validate using the claims parser
            Claims claims = Jwts.parser()
                    .setSigningKey(certificate.getPublicKey())
                    .parseClaimsJws(token.getToken()).getBody();
            token.setClaims(claims);
            return ValidationStatus.VALID;
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.debug(e);
            return ValidationStatus.INVALID;
        } catch (ExpiredJwtException e) {
            log.debug(e);
            return ValidationStatus.EXPIRED;
        }
    }

    private ValidationStatus checkRevocation(AAMClient aamClient, Token tokenForRevocation) {
        return aamClient.checkTokenRevocation(tokenForRevocation);
    }


    private X509Certificate getCA(AAMClient aamClient) throws CertificateException {
        String url = aamClient.getURL();
        X509Certificate aamX509Certificate = publicCertificates.get(url);
        if (aamX509Certificate == null) {
            aamX509Certificate = aamClient.getAAMRootCertificate();
            publicCertificates.put(url, aamX509Certificate);
        }
        return aamX509Certificate;
    }

}

