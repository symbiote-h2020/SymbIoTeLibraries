package eu.h2020.symbiote.security.token.jwt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.security.certificate.ECDSAHelper;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.enums.IssuingAuthorityType;
import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import eu.h2020.symbiote.security.exceptions.aam.JWTCreationException;
import eu.h2020.symbiote.security.exceptions.aam.MalformedJWTException;
import eu.h2020.symbiote.security.exceptions.aam.TokenValidationException;
import eu.h2020.symbiote.security.token.Token;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Set of functions for generating JWT tokens.
 *
 * @author Daniele Caldarola (CNIT)
 * @author Nemanja Ignjatov (UNIVIE)
 * @author Mikołaj Dobski (PSNC)
 */
public class JWTEngine {

    private static Log log = LogFactory.getLog(JWTEngine.class);

    private static SecureRandom random = new SecureRandom();

    /**
     * validates the token using the encoded issuer's public key and sets its claims
     *
     * @param token Token to be validated and initialized
     * @return validation status
     * @throws TokenValidationException on other errors
     */
    public static TokenValidationStatus validateTokenUsingIncludedIssuersPublicKey(Token token) throws TokenValidationException {
        try {
            ECDSAHelper.enableECDSAProvider();

            JWTClaims claims = getClaimsFromToken(token.getToken());
            //Convert IPK claim to publicKey for validation
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(claims.getIpk()));
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);

            //Validate token signature
            switch (validateToken(token, pubKey)) {
                case VALID:
                    return TokenValidationStatus.VALID;
                case EXPIRED:
                    return TokenValidationStatus.EXPIRED;
                case REVOKED:
                    return TokenValidationStatus.REVOKED;
                case INVALID:
                    return TokenValidationStatus.INVALID;
            }
        } catch (MalformedJWTException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e);
            throw new TokenValidationException("Token could not be validated", e);
        }
        return TokenValidationStatus.INVALID;
    }

    /**
     * Validates the token and sets its claims
     *
     * @param token     Token to be validated and initialized
     * @param publicKey issuer's public key
     * @return validation status
     * @throws TokenValidationException on other errors
     */
    public static TokenValidationStatus validateToken(Token token, PublicKey publicKey) throws TokenValidationException {
        try {
            ECDSAHelper.enableECDSAProvider();

            Claims claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token.getToken()).getBody();
            token.setClaims(claims);
            return TokenValidationStatus.VALID;
        } catch (ExpiredJwtException e) {
            log.error(e);
            return TokenValidationStatus.EXPIRED;
        } catch (SignatureException e) {
            log.error(e);
            return TokenValidationStatus.INVALID;
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error(e);
            throw new TokenValidationException("Token could not be validated", e);
        }
    }

    public static JWTClaims getClaimsFromToken(String jwtToken) throws MalformedJWTException {
        HashMap<String, Object> retMap = new HashMap<String, Object>();
        String[] jwtParts = jwtToken.split("\\.");
        if (jwtParts.length < AAMConstants.JWTPartsCount) {
            throw new MalformedJWTException();
        }
        //Get second part of the JWT
        String jwtBody = jwtParts[1];

        String claimsString = StringUtils.newStringUtf8(Base64.decodeBase64(jwtBody));

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> claimsMap = new HashMap<String, Object>();

        try {
            claimsMap = mapper.readValue(claimsString, new TypeReference<Map<String, String>>() {
            });

            Map<String, String> attributes = new HashMap<>();
            Set<String> jwtKeys = claimsMap.keySet();
            for (String key : jwtKeys) {
                Object value = claimsMap.get(key);
                if (key.startsWith(AAMConstants.SYMBIOTE_ATTRIBUTES_PREFIX))
                    attributes.put(key.substring(AAMConstants.SYMBIOTE_ATTRIBUTES_PREFIX.length()), (String) value);
                else
                    retMap.put(key, value);
            }

            //Extracting claims from JWT claims map
            JWTClaims retClaims = new JWTClaims(retMap.get("jti"), retMap.get("alg"), retMap.get("iss"), retMap.get("sub"), retMap
                    .get("iat"), retMap.get("exp"), retMap.get("ipk"), retMap.get("spk"), retMap.get("ttyp"), attributes);
            return retClaims;
        } catch (IOException e) {
            log.error(e);
            throw new MalformedJWTException(e);
        }
    }

    public static String generateJWTToken(String userId, Map<String, String> attributes, byte[] userPublicKey, IssuingAuthorityType deploymentType, Long tokenValidity, String deploymentID, PublicKey aamPublicKey, PrivateKey aamPrivateKey) throws JWTCreationException {
        ECDSAHelper.enableECDSAProvider();

        String jti = String.valueOf(random.nextInt());
        Map<String, Object> claimsMap = new HashMap<String, Object>();

        try {
            // Insert AAM Public Key
            claimsMap.put("ipk", Base64.encodeBase64String(aamPublicKey.getEncoded()));

            //Insert issuee Public Key
            claimsMap.put("spk", Base64.encodeBase64String(userPublicKey));

            //Add symbIoTe related attributes to token
            if (attributes != null && !attributes.isEmpty()) {
                for (Map.Entry<String, String> entry : attributes.entrySet()) {
                    claimsMap.put(AAMConstants.SYMBIOTE_ATTRIBUTES_PREFIX + entry.getKey(), entry.getValue());
                }
            }

            //Insert token type based on AAM deployment type (Core or Platform)
            switch (deploymentType) {
                case CORE:
                    claimsMap.put(AAMConstants.CLAIM_NAME_TOKEN_TYPE, IssuingAuthorityType.CORE);
                    break;
                case PLATFORM:
                    claimsMap.put(AAMConstants.CLAIM_NAME_TOKEN_TYPE, IssuingAuthorityType.PLATFORM);
                    break;
                case NULL:
                    throw new JWTCreationException("uninitialized deployment type, must be CORE or PLATFORM");
            }

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setClaims(claimsMap);
            jwtBuilder.setId(jti);
            jwtBuilder.setIssuer(deploymentID);
            jwtBuilder.setSubject(userId);
            jwtBuilder.setIssuedAt(new Date());
            jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + tokenValidity));
            jwtBuilder.signWith(SignatureAlgorithm.ES256, aamPrivateKey);

            return jwtBuilder.compact();
        } catch (Exception e) {
            String message = "JWT creation error";
            log.error(message, e);
            throw new JWTCreationException(message, e);
        }
    }
}

