package eu.h2020.symbiote;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import eu.h2020.symbiote.SecurityHandlerTest.DateUtil;
import eu.h2020.symbiote.commons.security.constants.SHConstants;
import eu.h2020.symbiote.commons.security.messaging.bean.Credential;
import eu.h2020.symbiote.commons.security.messaging.bean.Status;
import eu.h2020.symbiote.commons.security.messaging.bean.Token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/*
 * @author: Elena Garrido
 * @version: 12/02/2017
 */
@RestController
@WebAppConfiguration
public class CoreAndPlatformAAMDummyServer {
  private static final Log logger = LogFactory.getLog(CoreAndPlatformAAMDummyServer.class);
  
  @RequestMapping(method = RequestMethod.GET, path = SHConstants.GET_CORE_AAM_CA_CERTIFICATE)
  public byte[] getRootCertificate() {
	  logger.debug("invoked get token public");
	  String pemFile =
		 "-----BEGIN CERTIFICATE-----\n"+
		 "MIIDczCCAlugAwIBAgIEXPARpzANBgkqhkiG9w0BAQsFADBqMQswCQYDVQQGEwJF\n"+
		 "UzESMBAGA1UECBMJQmFyY2Vsb25hMRIwEAYDVQQHEwlCYXJjZWxvbmExDTALBgNV\n"+
		 "BAoTBEF0b3MxDDAKBgNVBAsTA0FSSTEWMBQGA1UEAxMNVGVzdCBTeW1iaW90ZTAe\n"+
		 "Fw0xNzAzMjExMzU4NTVaFw0xNzA2MTkxMzU4NTVaMGoxCzAJBgNVBAYTAkVTMRIw\n"+
		 "EAYDVQQIEwlCYXJjZWxvbmExEjAQBgNVBAcTCUJhcmNlbG9uYTENMAsGA1UEChME\n"+
		 "QXRvczEMMAoGA1UECxMDQVJJMRYwFAYDVQQDEw1UZXN0IFN5bWJpb3RlMIIBIjAN\n"+
		 "BgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuPjU1Qrw9sZWYztUTiCpsqU8lZp\n"+
		 "Ig414LzGXbK/W7oe3njDoE1P5S1y3nNJgksNzzGNunR84OrEb5A6BND2otYfblEB\n"+
		 "JsFs/ziqC94EatOQTpiy09P8GMtG4fTrImAMQOTK/Qa06K1KvNgUNg9OrOlLE7M+\n"+
		 "sYEaCJE97ftoiQWoCXf9hEPLOv6f1j/Wt5atJ72Ebs3DGOl1EP4JLrxVFlVhvdwj\n"+
		 "v5Z7SQ9AEvkzK7guKggwwYs1zzjxb9iexvDzHNq/td1NL5i7ULhDQg+cWjqDYwRk\n"+
		 "2Qb2sscVcn+8q8fLV3xD+q5FZSFDNG8odmClbk90tYpVadzMfSKLBFBlcwIDAQAB\n"+
		 "oyEwHzAdBgNVHQ4EFgQUytEsSR/Kckc1UeVCAg86yrJVOCswDQYJKoZIhvcNAQEL\n"+
		 "BQADggEBAFfDTeR3lkWHlSnGWLPCDLAsWIXUTgLGpHao2fsGXR1kxzaYOUlv9HhV\n"+
		 "/bqF5aPsnjKl8JrxZJ1mVUvz7V2qIvmtmmqrqbEh1GF6qhOgmbM/nyx6K/BvESKx\n"+
		 "+XMVjInj5SND1RhrjR2AOzGHnC/BybsPknaGJ7C8gxFnA6qOXolH0+vtZ/d1hNCG\n"+
		 "Hz/cckIa10GYFTJacr4Maj/MLvYOS8VEN19LM1hFxCooKJQH70L5kzCAyI7omWt1\n"+
		 "AzWTKOPAB+BYXbgr/vX316qSVYT6T5pF13DY5lva1mXPOes8pnR53EA5IqvMzKfX\n"+
		 "wTy8cAqkLUUrJoARWuoocn5B9qpZMAE=\n"+
		 "-----END CERTIFICATE-----\n";
	  	logger.debug("invoked get token certificate");
		return pemFile.getBytes();
  }
  
  @RequestMapping(method = RequestMethod.POST, path = SHConstants.DO_CORE_AAM_LOGIN,  produces = "application/json", consumes = "application/json")
  public @ResponseBody Token doLogin(@RequestBody Credential credential) {
	  logger.info("User trying to login "+credential.getUser()+ " - "+credential.getPasswd());
	  Token token = new Token();
	  token.setToken("eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImV4cCI6MTQ5MDI3ODIyMSwibmFtZSI6InRlc3QyIn0.V2qYTXOp1Xv1jSXZaxn-pbr_Byhmhuu6fAMy0fytco1JgJpvxTw5wlhJ1GuAvuA71IRmINyCAgcUo4oBrXFd4Wy_NthR3pQ5YIflD2t31RoVD1QQlhARri6A-mkjj4rVbsU98BG3ixvdYTkAjiLUbpvNrqm2Y3cDstaLWcSfGzN7ulVuMbEUWbZj9rkW_G4VF62vvOXL9C8UsxYyV0qx9dPzy2iiMGJQ-s16dYb5jiFY5BfvxUf3TWRJPhe5eaX5X7oDvzNh4JDWAFxoKYEH2PvoHctknX5Kon0HBCV_8xmJtxwlKB3lzeugqqFQW8HQiAqSbTAhkcmK9QGs_zkmyA");
	  return token;
  }
  

  @RequestMapping(method = RequestMethod.POST, path = SHConstants.DO_CORE_AAM_CHECK_TOKEN_REVOCATION, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
  public @ResponseBody Status checkTokenRevocation(@RequestBody Token token) {
	  logger.info("Checking token revocation "+token);
	  Status status = new Status();
	  status.setStatus(Status.SUCCESS);
	  return status;
  }       

  @RequestMapping(method = RequestMethod.POST, path = SHConstants.DO_REQUEST_CORE_TOKEN, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
  public @ResponseBody Token requestCoreToken(@RequestBody Token homeToken) {
	  final String ALIAS = "mytest";
	  logger.info("Requesting core token, received home token "+homeToken.getToken());
	  try {
		  KeyStore  ks = KeyStore.getInstance("JKS");
		  InputStream readStream = new FileInputStream("./src/test/resources/certificates/mytest.jks");// Use file stream to load from file system or class.getResourceAsStream to load from classpath
		  ks.load(readStream, "password".toCharArray());
		  Key key = ks.getKey(ALIAS, "password".toCharArray());
		  readStream.close();
		  String tokenString=  Jwts.builder()
				  .setSubject("coreAAM")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("name1", "value1")
				  .claim("name2", "value2")
				  .claim("name3", "value3")
				  .signWith(SignatureAlgorithm.RS512, key)
				  .compact();
		  Token coreToken = new Token();
		  coreToken.setToken(tokenString);
		  return coreToken;
	  } catch (KeyStoreException |  NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException e) {
		  logger.error(e);
	  }
	  return null;
  }       

  @RequestMapping(method = RequestMethod.POST, path = SHConstants.DO_REQUEST_FOREIGN_TOKEN, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
  public @ResponseBody Token requestForeignToken(@RequestBody Token homeToken) {
	  final String ALIAS = "mytest";
	  logger.info("Requesting foreign token, received home token "+homeToken.getToken());
	  try {
		  KeyStore  ks = KeyStore.getInstance("JKS");
		  InputStream readStream = new FileInputStream("./src/test/resources/certificates/mytest.jks");// Use file stream to load from file system or class.getResourceAsStream to load from classpath
		  ks.load(readStream, "password".toCharArray());
		  Key key = ks.getKey(ALIAS, "password".toCharArray());
		  readStream.close();
		  String tokenString=  Jwts.builder()
				  .setSubject("foreign")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("fname1", "fvalue1")
				  .claim("fname2", "fvalue2")
				  .claim("fname3", "fvalue3")
				  .signWith(SignatureAlgorithm.RS512, key)
				  .compact();
		  Token coreToken = new Token();
		  coreToken.setToken(tokenString);
		  return coreToken;
	  } catch (KeyStoreException |  NoSuchAlgorithmException | CertificateException | IOException | UnrecoverableKeyException e) {
		  logger.error(e);
	  }
	  return null;
  }       


}

