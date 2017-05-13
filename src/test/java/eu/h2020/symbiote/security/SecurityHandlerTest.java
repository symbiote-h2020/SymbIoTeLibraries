package eu.h2020.symbiote.security;

import eu.h2020.symbiote.security.aams.DummyAAMAMQPLoginListener;
import eu.h2020.symbiote.security.certificate.Certificate;
import eu.h2020.symbiote.security.certificate.CertificateVerificationException;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.enums.IssuingAuthorityType;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.exceptions.aam.TokenValidationException;
import eu.h2020.symbiote.security.session.AAM;
import eu.h2020.symbiote.security.token.Token;
import eu.h2020.symbiote.security.token.jwt.JWTEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * This class handles the initialization from the platform. Initially created by jose
 *
 * @author Elena Garrido (Atos)
 * @author Mikołaj Dobski (PSNC)
 *         \class PlatformInformationManager
 *         \brief PlatformInformationManager handles the registration of the resources within the platform
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, properties = {"symbiote.testaam" +
        ".url=http://localhost:18033", "symbiote.coreaam.url=http://localhost:18033"})
@ContextConfiguration(locations = {"classpath:test-properties.xml"})
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class SecurityHandlerTest {

    private static final Log log = LogFactory.getLog(SecurityHandlerTest.class);
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private SecurityHandler securityHandler;
    private String coreTokenString;
    private String platformTokenString;
    private String symbioteCoreInterfaceAddress;
    private AAM coreAAM;
    private String rabbitMQHostIP;
    private DummyAAMAMQPLoginListener dummyAAMAMQPLoginListener = new DummyAAMAMQPLoginListener();

    @Before
    public void setUp() throws Exception {
        dummyAAMAMQPLoginListener.init();
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());


        symbioteCoreInterfaceAddress = "http://localhost:18033";
        rabbitMQHostIP = "localhost";
        securityHandler = new SecurityHandler(symbioteCoreInterfaceAddress, rabbitMQHostIP, "guest", "guest");

        final String ALIAS = "test aam keystore";
        KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
        ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
        Key key = ks.getKey(ALIAS, "1234567".toCharArray());

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("name", "test2");
        coreTokenString = JWTEngine.generateJWTToken("test1", attributes, ks.getCertificate(ALIAS).getPublicKey()
                        .getEncoded(), IssuingAuthorityType.CORE, DateUtil.addDays(new Date(), 1).getTime(),
                "securityHandlerTestCoreAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);
        platformTokenString = JWTEngine.generateJWTToken("test1", attributes, ks.getCertificate(ALIAS).getPublicKey()
                        .getEncoded(), IssuingAuthorityType.PLATFORM, DateUtil.addDays(new Date(), 1).getTime(),
                "securityHandlerTestPlatformAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);

        // coreAAM
        X509Certificate x509Certificate = (X509Certificate) ks.getCertificate("test aam keystore");
        StringWriter signedCertificatePEMDataStringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(signedCertificatePEMDataStringWriter);
        pemWriter.writeObject(x509Certificate);
        pemWriter.close();
        // XXX the instance id "PlatformAAM" is hardcoded in the keystore
        coreAAM = new AAM(symbioteCoreInterfaceAddress, "Core AAM", "PlatformAAM", new Certificate(signedCertificatePEMDataStringWriter.toString()));
    }


    @Test
    public void testValidation() {
        try {
            final String ALIAS = "test aam keystore";
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            Assert.assertTrue(securityHandler.certificateValidation(ks));
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException |
                CertificateVerificationException | NoSuchProviderException e) {
            log.error(e);
            assert (false);
        }
    }


    @Test
    public void testRequestFederatedCoreToken() {
        try {
            Token token = securityHandler.requestFederatedCoreToken("user", "password");
            Assert.assertTrue(IssuingAuthorityType.CORE == token.getType());
        } catch (SecurityHandlerException e) {
            log.error(e);
            assert (false);
        }
    }

    @Test
    public void testRequestForeignToken() {
        try {
            Token token = securityHandler.requestFederatedCoreToken("user", "password");

            log.info("Test Client received this Token: " + token.toString());

            assertNotNull(token.getToken());
            assertEquals(IssuingAuthorityType.CORE, token.getType());

            List<AAM> aams = new ArrayList<>();
            aams.add(new AAM(symbioteCoreInterfaceAddress, "Core AAM", "coreAAM", new Certificate()));
            Map<String, Token> tokens = securityHandler.requestForeignTokens(aams);
            assert (tokens != null);
        } catch (SecurityHandlerException e) {
            log.error(e);
            assert (false);
        }
    }

    @Test
    public void testRequestCoreTokenUsingCoreCredentials() {
        Token token = securityHandler.requestCoreToken("Test1", "Test1");
        Assert.assertNotNull(token);
        assertEquals(IssuingAuthorityType.CORE, token.getType());
    }


    @Test
    public void testCoreTokenValidation() {
        try {
            Token token = new Token(coreTokenString);
            // TODO rework this API to return TokenValidationStatus instead of being exception driven for normal usage
            securityHandler.verifyCoreToken(token);
            Assert.assertEquals("test1", token.getClaims().getSubject());
            Assert.assertEquals("test2", token.getClaims().get(AAMConstants.SYMBIOTE_ATTRIBUTES_PREFIX + "name"));
        } catch (TokenValidationException e) {
            log.error(e);
            assert (false);
        }
    }

    @Test
    public void testCoreTokenValidationWithError() throws TokenValidationException {
        thrown.expect(TokenValidationException.class);
        String tokenString =
                "eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImV4cCI6MTQ5MTAzNzk5MiwibmFtZSI6InRlc3QyIn0.j8EPRRVi5L63-s5r8lI9vq_Pi_NoPy4Q-jn39xg8zETTpYecoC26xMo5XaE-sJjhZ1Mup-W1njV3g7QMVJUY2G_gqzezuSc1oUs9ZVYabGKI4W8D1jkWZo9-FQTPJw8_Zy8jeU1UZD8Vwcn6u51zw7dDuFA-tcFoYpK99GyCAqkukm1H7dCfAr-bIWeiOEI8p2KHc2-3vZto39hGMrexCigWI1dSICw2rG1mESyZgxrT4cs1UEQp1KuQ1WK2nUOhjeNTozpvqs65weKw4aCiQgvp36-UxUvRJPl7KBydvFf564T0gHEtgmXSZMQGHwUI9x6RUFR4NuvtGeAFU2pcx";
        Token token = new Token(tokenString);
        // TODO rework this API to return TokenValidationStatus instead of being exception driven for normal usage
        securityHandler.verifyCoreToken(token);
    }

    /**
     * As the Core AAM and Platform AAM expose the same interface we can use the core AAM for this test
     */
    @Test
    public void testForeignPlatformTokenValidation() {
        try {
            Token token = new Token(platformTokenString);
            securityHandler.verifyPlatformToken(coreAAM, token);
            Assert.assertTrue(token.getType() == IssuingAuthorityType.PLATFORM);
            Assert.assertEquals("test1", token.getClaims().getSubject());
            Assert.assertEquals("test2", token.getClaims().get(AAMConstants.SYMBIOTE_ATTRIBUTES_PREFIX + "name"));
        } catch (TokenValidationException e) {
            log.error(e);
            assert (false);
        }
    }

    @Test
    public void getAvailableAAMsCollectionFromCoreAAM() throws SecurityHandlerException {
        List<AAM> aams = securityHandler.getAvailableAAMs();

        // for this test the dummy REST service returns only the core AAM
        assertEquals(1, aams.size());

        // verifying the contents
        AAM aam = aams.get(0);
        // this expected PlatformAAM is due to the value stored in the issued certificate in the test keystore
        assertEquals("Symbiote Core", aam.getAamInstanceId());
        assertEquals("https://localhost:8100", aam.getAamAddress());
        // maybe we could externalize it to spring config
        assertEquals("SymbIoTe Core AAM", aam.getAamInstanceFriendlyName());
        assertEquals("coreCertTestValue", aam.getCertificate().getCertificateString());
    }

    @Test
    public void getCACertOverRESTSuccess() {
        // dirty definition of HttpClient to connect to HTTPS endpoints.
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            log.error(e);
            assert (false);
        }

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        // Test rest template
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        ResponseEntity<String> response = restTemplate.getForEntity(symbioteCoreInterfaceAddress + AAMConstants
                .AAM_GET_CA_CERTIFICATE, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            X509Certificate x509Certificate = (X509Certificate) ks.getCertificate("test aam keystore");
            StringWriter signedCertificatePEMDataStringWriter = new StringWriter();
            JcaPEMWriter pemWriter = new JcaPEMWriter(signedCertificatePEMDataStringWriter);
            pemWriter.writeObject(x509Certificate);
            pemWriter.close();
            assertEquals(signedCertificatePEMDataStringWriter.toString(), response.getBody());
        } catch (IOException | NoSuchProviderException | KeyStoreException | CertificateException |
                NoSuchAlgorithmException e) {
            log.error(e);
            assert (false);
        }
    }

    static public class DateUtil {
        public static Date addDays(Date date, int days) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }
    }
}

