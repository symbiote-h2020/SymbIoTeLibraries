package eu.h2020.symbiote;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import eu.h2020.symbiote.commons.security.token.SymbIoTeToken;
import eu.h2020.symbiote.commons.security.SecurityHandler;
import eu.h2020.symbiote.commons.security.certificate.CertificateVerificationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**! \class PlatformInformationManager
 * \brief PlatformInformationManager handles the registration of the resources within the platform
 **/

/**
 * This class handles the initialization from the platform. Initially created by jose
 *
 * @author: Elena Garrido
 * @version: 06/10/2016
 */
@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT, properties = {"symbiote.testaam.url=http://localhost:8033", "symbiote.coreaam.url=http://localhost:8033"})
@ContextConfiguration(locations = {"classpath:test-properties.xml" })
@Configuration
@ComponentScan
@EnableAutoConfiguration

public class SecurityHandlerTest {
	    
  private static final Log logger = LogFactory.getLog(SecurityHandlerTest.class);

  private SecurityHandler securityHandler;

  @Value("${symbiote.testaam.url}")
  private String aamUrl;
  
  @Before
  public void setUp() throws Exception {
  	String coreAAMUrl = "http://localhost:8033";
  	String rabbitMQHostIP = "localhost";
    securityHandler = new SecurityHandler(coreAAMUrl, rabbitMQHostIP);
  }


  @Test
  public void testValidation() {
		try {
			KeyStore p12 = KeyStore.getInstance("pkcs12");
	        p12.load(new FileInputStream("./src/test/resources/certificates/mytest.p12"), "password".toCharArray());
	        boolean bolTrue = securityHandler.certificateValidation(p12);
	        
			p12 = KeyStore.getInstance("pkcs12");
	        p12.load(new FileInputStream("./src/test/resources/certificates/dianne.p12"), "password".toCharArray());
	        boolean bolFalse = securityHandler.certificateValidation(p12);

	        assert(bolTrue && (bolFalse==false));
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | CertificateVerificationException e) {
			logger.error(e);
	        assert(false);
		}
  }


  @Test
  public void testRequestCoreToken() {
	  	SymbIoTeToken token = securityHandler.requestCoreToken("user", "password");
        assert(token!=null);
  }

  @Test
  public void testRequestForeignToken() {
	  securityHandler.requestCoreToken("user", "password");
	  ArrayList<String> urllist = new ArrayList<String>();
	  urllist.add(aamUrl);
	  HashMap<String, SymbIoTeToken>tokens = securityHandler.requestForeignTokens(urllist);
      assert(tokens!=null);
  }

  @Test
  public void testRequestCoreTokenFromApplication() {
	  	SymbIoTeToken token = securityHandler.appRequestCoreToken("user", "password");
        assert(token!=null);
  }
  
  
  @Test
  public void testCoreTokenValidation(){
	  final String ALIAS = "mytest";
	  try{
		  KeyStore ks = KeyStore.getInstance("JKS");
		  InputStream readStream = new FileInputStream("./src/test/resources/certificates/mytest.jks");// Use file stream to load from file system or class.getResourceAsStream to load from classpath
		  ks.load(readStream, "password".toCharArray());
		  Key key = ks.getKey(ALIAS, "password".toCharArray());
		  readStream.close();

		  String tokenString=  Jwts.builder()
				  .setSubject("test1")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("name", "test2")
				  .signWith(SignatureAlgorithm.RS512, key)
				  .compact();
		  SymbIoTeToken token = securityHandler.verifyCoreToken(tokenString);
		  boolean result =  "test1".equals(token.getClaim(SymbIoTeToken.JWT_CLAIMS_SUBJECT));
		  result  &=  "test2".equals(token.getClaim("name"));
		  assert(result);
	  }catch(Throwable t){
		  logger.error(t);
		  assert(false);
	  }
			
	}

  @Test
  public void testCoreTokenValidationWithError(){
	  try{
		  String tokenString = "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImV4cCI6MTQ5MTAzNzk5MiwibmFtZSI6InRlc3QyIn0.j8EPRRVi5L63-s5r8lI9vq_Pi_NoPy4Q-jn39xg8zETTpYecoC26xMo5XaE-sJjhZ1Mup-W1njV3g7QMVJUY2G_gqzezuSc1oUs9ZVYabGKI4W8D1jkWZo9-FQTPJw8_Zy8jeU1UZD8Vwcn6u51zw7dDuFA-tcFoYpK99GyCAqkukm1H7dCfAr-bIWeiOEI8p2KHc2-3vZto39hGMrexCigWI1dSICw2rG1mESyZgxrT4cs1UEQp1KuQ1WK2nUOhjeNTozpvqs65weKw4aCiQgvp36-UxUvRJPl7KBydvFf564T0gHEtgmXSZMQGHwUI9x6RUFR4NuvtGeAFU2pcx";
		  securityHandler.verifyCoreToken(tokenString);
		  assert(false);
	  }catch(Throwable t){
		  logger.debug("Exception correctly thrown form the sofware", t);
		  assert(true);
	  }
			
	}

  @Test
  public void testForeignPlatformTokenValidation(){
	  final String ALIAS = "mytest";
	  try{
		  KeyStore ks = KeyStore.getInstance("JKS");
		  InputStream readStream = new FileInputStream("./src/test/resources/certificates/mytest.jks");// Use file stream to load from file system or class.getResourceAsStream to load from classpath
		  ks.load(readStream, "password".toCharArray());
		  Key key = ks.getKey(ALIAS, "password".toCharArray());
		  readStream.close();

		  String tokenString=  Jwts.builder()
				  .setSubject("test1")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("name", "test2")
				  .signWith(SignatureAlgorithm.RS512, key)
				  .compact();
		  SymbIoTeToken token = securityHandler.verifyForeignPlatformToken(aamUrl, tokenString);
		  boolean result =  "test1".equals(token.getClaim(SymbIoTeToken.JWT_CLAIMS_SUBJECT));
		  result  &=  "test2".equals(token.getClaim("name"));
		  assert(result);
	  }catch(Throwable t){
		  logger.error(t);
		  assert(false);
	  }
			
	}

	static public class DateUtil
	{
	    public static Date addDays(Date date, int days)
	    {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, days); //minus number would decrement the days
	        return cal.getTime();
	    }
	}

  
}

