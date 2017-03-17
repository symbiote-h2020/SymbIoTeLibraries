package eu.h2020.symbiote;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

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


  @Test
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

  @Test
  public void testLogin() {
        assert(securityHandler.login("user", "password"));
  }

}
