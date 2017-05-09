package eu.h2020.symbiote.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.enums.IssuingAuthorityType;
import eu.h2020.symbiote.security.exceptions.aam.JWTCreationException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import eu.h2020.symbiote.security.token.jwt.JWTEngine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlatformAAMDummyServer {
    private static final Log log = LogFactory.getLog(PlatformAAMDummyServer.class);
    private static Log logger = LogFactory.getLog(PlatformAAMDummyServer.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Spring AMQP Listener for resource registration requests. This method is invoked when Registration
     * Handler sends a resource registration request and it is responsible for forwarding the message
     * to the symbIoTe core. As soon as it receives a reply, it manually sends back the response
     * to the Registration Handler via the appropriate message queue by the use of the RestAPICallback.
     *
     * @param headers The AMQP headers
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_QUEUE, durable = "true",
                    autoDelete = "false", exclusive = "false"),
            exchange = @Exchange(value = SecurityHandlerConstants.EXCHANGE_NAME, ignoreDeclarationExceptions = "true"),
            key = SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_ROUTING_KEY)
    )
    public void resourceRegistration(Message message, @Headers() Map<String, String> headers) {
        logger.info("resourceRegistration" + new String(message.getBody()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            Credentials credential = mapper.readValue(new String(message.getBody()), Credentials.class);
            logger.info("User trying to login " + credential.getUsername() + " - " + credential.getPassword());

            final String ALIAS = "test aam keystore";
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ks.load(new FileInputStream("./src/test/resources/TestAAM.keystore"), "1234567".toCharArray());
            Key key = ks.getKey(ALIAS, "1234567".toCharArray());

            HashMap<String, String> attributes = new HashMap<>();
            attributes.put("name", "test2");
            String tokenString = JWTEngine.generateJWTToken(credential.getUsername(), attributes, ks.getCertificate(ALIAS).getPublicKey().getEncoded(), IssuingAuthorityType.PLATFORM, SecurityHandlerTest.DateUtil.addDays(new Date(), 1).getTime(), "securityHandlerTestPlatformAAM", ks.getCertificate(ALIAS).getPublicKey(), (PrivateKey) key);

            String response = mapper.writeValueAsString(new Token(tokenString));
            rabbitTemplate.convertAndSend(headers.get("amqp_replyTo"), response.getBytes(),
                    m -> {
                        Object a = headers.get("amqp_correlationId");
                        // XXX not sure why the correlationIdString is empty and forces us to use deprecated API
                        m.getMessageProperties().setCorrelationId((byte[]) a);
                        return m;
                    });
        } catch (IOException | JWTCreationException | NoSuchAlgorithmException | CertificateException | NoSuchProviderException | UnrecoverableKeyException | KeyStoreException e) {
            log.error(e);
        }
    }


}

