package eu.h2020.symbiote.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.SecurityHandler;
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
@Component
public class SHTester {

  private static final Log logger = LogFactory.getLog(SHTester.class);

  @Autowired SecurityHandler secHandler;

  @PostConstruct
  private void init() {
		try {
			KeyStore p12 = KeyStore.getInstance("pkcs12");
	        p12.load(new FileInputStream("C:/SymbIoTe/git/R2/SecurityHandler/certificates/dianne.p12"), "password".toCharArray());
			secHandler.certificateValidation(p12);
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | CertificateVerificationException e) {
			logger.error(e);
		}
  }


}
