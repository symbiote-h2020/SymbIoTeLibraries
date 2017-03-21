package eu.h2020.symbiote;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;



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
	  final String ALIAS = "mytest";
	  try{
		  
		  KeyStore ks = KeyStore.getInstance("JKS");
		  InputStream readStream = new FileInputStream("./certificates/mytest.jks");// Use file stream to load from file system or class.getResourceAsStream to load from classpath
		  ks.load(readStream, "password".toCharArray());
	      Enumeration<String> e = ks.aliases();
	      while (e.hasMoreElements()) {
	          String alias = e.nextElement();
	          System.out.println(alias);
	      }
		  Key key = ks.getKey(ALIAS, "password".toCharArray());
		  readStream.close();
	      Certificate certificate = ks.getCertificate(ALIAS);

		  String token=  Jwts.builder()
				  .setSubject("test1")
				  .setExpiration( DateUtil.addDays(new Date(), 1))
				  .claim("name", "test2")
				  .claim("scope", "test3")
				  .signWith(SignatureAlgorithm.RS512, key)
				  .compact();
			System.out.println(token);
			
			JwtParser dp = Jwts.parser();
			Jws<Claims> jws = dp.
					setSigningKey(certificate.getPublicKey()).
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
