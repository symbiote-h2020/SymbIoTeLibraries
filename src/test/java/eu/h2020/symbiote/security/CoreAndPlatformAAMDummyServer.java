package eu.h2020.symbiote.security;


import eu.h2020.symbiote.security.SecurityHandlerTest.DateUtil;
import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.enums.IssuingAuthorityType;
import eu.h2020.symbiote.security.enums.TokenValidationStatus;
import eu.h2020.symbiote.security.exceptions.aam.JWTCreationException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import eu.h2020.symbiote.security.token.jwt.JWTEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;


/*
 * @author: Elena Garrido
 * @version: 12/02/2017
 */
@RestController
@WebAppConfiguration
public class CoreAndPlatformAAMDummyServer {
    private static final Log logger = LogFactory.getLog(CoreAndPlatformAAMDummyServer.class);

    public CoreAndPlatformAAMDummyServer() {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    @RequestMapping(method = RequestMethod.GET, path = SecurityHandlerConstants.GET_CORE_AAM_CA_CERTIFICATE)
    public byte[] getRootCertificate() {
        logger.debug("invoked get token public");
        String pemFile = "-----BEGIN CERTIFICATE-----\n" +
                "MIIBPjCB5aADAgECAgEBMAoGCCqGSM49BAMCMCkxFDASBgNVBCkMC1BsYXRmb3Jt\n" +
                "QUFNMREwDwYDVQQKDAhTWU1CSU9URTAeFw0xNzAzMzExMDMzNDJaFw0zNzAzMjYx\n" +
                "MDMzNDJaMCkxFDASBgNVBCkMC1BsYXRmb3JtQUFNMREwDwYDVQQKDAhTWU1CSU9U\n" +
                "RTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABEyxvMP4rB3CA12QDN5boE93ZhLz\n" +
                "+vOJdWTtoaHCKOYbEgibYUdj4fhynkNFmEp0jzKCruxhXyBgVhpbcN0ZE9kwCgYI\n" +
                "KoZIzj0EAwIDSAAwRQIgH+SQ4fmyPupmvUWfwt1PEEYK1aH2h8OtpeUgcEj8s/wC\n" +
                "IQCW5hMbyWbBnUQUlhHHPWXUCo5ax0cEKe2J89cBRbC6MA==\n" +
                "-----END CERTIFICATE-----";
        logger.debug("invoked get token certificate");
        return pemFile.getBytes();
    }

    @RequestMapping(method = RequestMethod.POST, path = SecurityHandlerConstants.DO_CORE_AAM_LOGIN, produces = "application/json", consumes = "application/json")
    public @ResponseBody
    Token doLogin(@RequestBody Credentials credential) {
        logger.info("User trying to login " + credential.getUsername() + " - " + credential.getPassword());
        Token token = new Token();
        try {
            final String ALIAS = "test aam keystore";
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            Key key = ks.getKey(ALIAS, "1234567".toCharArray());

            HashMap<String, String> attributes = new HashMap<>();
            attributes.put("name", "test2");
            String tokenString = JWTEngine.generateJWTToken(credential.getUsername(), attributes, ks.getCertificate(ALIAS).getPublicKey().getEncoded(), IssuingAuthorityType.CORE, DateUtil.addDays(new Date(), 1).getTime(), "securityHandlerTestAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);

            Token coreToken = new Token();
            coreToken.setToken(tokenString);
            return coreToken;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException | JWTCreationException | NoSuchProviderException e) {
            logger.error(e);
        }
        return token;
    }


    @RequestMapping(method = RequestMethod.POST, path = SecurityHandlerConstants.DO_CORE_AAM_CHECK_TOKEN_REVOCATION, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody
    TokenValidationStatus checkTokenRevocation(@RequestBody Token token) {
        logger.info("Checking token revocation " + token);
        // todo implement... for the moment returns valid
        return TokenValidationStatus.VALID;
    }

    @RequestMapping(method = RequestMethod.POST, path = SecurityHandlerConstants.DO_REQUEST_CORE_TOKEN, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody
    Token requestCoreToken(@RequestBody Token homeToken) {
        logger.info("Requesting core token, received home token " + homeToken.getToken());
        try {
            final String ALIAS = "test aam keystore";
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            Key key = ks.getKey(ALIAS, "1234567".toCharArray());

            HashMap<String, String> attributes = new HashMap<>();
            attributes.put("name", "test2");
            String tokenString = JWTEngine.generateJWTToken("test1", attributes, ks.getCertificate(ALIAS).getPublicKey().getEncoded(), IssuingAuthorityType.CORE, DateUtil.addDays(new Date(), 1).getTime(), "securityHandlerTestAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);

            Token coreToken = new Token();
            coreToken.setToken(tokenString);
            return coreToken;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException | JWTCreationException | NoSuchProviderException e) {
            logger.error(e);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, path = SecurityHandlerConstants.DO_REQUEST_FOREIGN_TOKEN, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public @ResponseBody
    Token requestForeignToken(@RequestBody Token homeToken) {
        logger.info("Requesting foreign token, received home token " + homeToken.getToken());
        try {
            final String ALIAS = "test aam keystore";
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            Key key = ks.getKey(ALIAS, "1234567".toCharArray());

            HashMap<String, String> attributes = new HashMap<>();
            attributes.put("fname1", "fvalue1");
            attributes.put("fname2", "fvalue2");
            attributes.put("fname3", "fvalue3");
            String tokenString = JWTEngine.generateJWTToken("foreign", attributes, ks.getCertificate(ALIAS).getPublicKey().getEncoded(), IssuingAuthorityType.CORE, DateUtil.addDays(new Date(), 1).getTime(), "securityHandlerTestAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);

            Token coreToken = new Token();
            coreToken.setToken(tokenString);
            return coreToken;
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException | NoSuchProviderException | JWTCreationException e) {
            logger.error(e);
        }
        return null;
    }


}

