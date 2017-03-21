package eu.h2020.symbiote;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import eu.h2020.symbiote.certificate.CertificateVerificationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest( webEnvironment = WebEnvironment.DEFINED_PORT, properties = "symbiote.coreaam.uri=http://localhost:8033")
@ContextConfiguration(locations = {"classpath:test-properties.xml" })
@Configuration
@ComponentScan
@EnableAutoConfiguration

public class SecurityHandlerTest {
	    
  private static final Log logger = LogFactory.getLog(SecurityHandlerTest.class);

  @Autowired SecurityHandler securityHandler;

  @Before
  public void setUp() throws Exception {
  }


//  @Test
  public void testValidation() {
		try {
			KeyStore p12 = KeyStore.getInstance("pkcs12");
	        p12.load(new FileInputStream("./certificates/dianne.p12"), "password".toCharArray());
	        assert(securityHandler.certificateValidation(p12));
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | CertificateVerificationException e) {
			logger.error(e);
	        assert(false);
		}
  }

//  @Test
  public void testLogin() {
        assert(securityHandler.login("user", "password"));
  }

  @Test
  public void testTokenValidation(){
	  try{
		  
/*		  KeyStore p12 = KeyStore.getInstance("pkcs12");
	      p12.load(new FileInputStream("./certificates/dianne.p12"), "password".toCharArray());
	      Enumeration<String> e = p12.aliases();
	      String alias = null;
	      while (e.hasMoreElements()) {
	          alias = e.nextElement();
	      }
	      Key key = p12.getKey(alias, "password".toCharArray());
	      Certificate certificate = p12.getCertificate(alias);*/
	      SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
	   // get base64 encoded version of the key
	      String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
	      System.out.println(encodedKey);
	      
		  String token=  Jwts.builder()
				  .setSubject("test1")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("name", "test2")
				  .claim("scope", "test3")
				  //.signWith(SignatureAlgorithm.HS256,"secret".getBytes())
				  .signWith(SignatureAlgorithm.HS256, secretKey)
				  .compact();
			System.out.println(token);
			
			byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
			// rebuild key using SecretKeySpec
			SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");			
			/*byte[] converter = "secret".getBytes();*/
			JwtParser dp = Jwts.parser();
			Jws<Claims> jws = dp.
					//setSigningKey(converter).
					setSigningKey(originalKey).
					parseClaimsJws(token);
			Claims claims = jws.getBody();
			System.out.println(claims.getSubject());
			System.out.println(claims.get("name"));
	  }catch(Throwable t){
		  t.printStackTrace();
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
