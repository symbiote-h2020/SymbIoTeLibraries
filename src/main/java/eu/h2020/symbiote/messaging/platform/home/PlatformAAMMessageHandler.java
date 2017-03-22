package eu.h2020.symbiote.messaging.platform.home;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;

@Component
public class PlatformAAMMessageHandler {
	private static Log logger = LogFactory.getLog(PlatformAAMMessageHandler.class);
    private static final String EXCHANGE_NAME = "HOME_LOGIN";
    private static final String PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY = "PLATOFRMAAM_LOGIN_TOKEN";
    private static final String PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY = "PLATOFRMAAM_LOGIN_TOKEN_REPLY";

    
	@Autowired
	private ApplicationContext applicationContext;

    public Token login(Credential credential) {
        try {
            logger.info("Sending request of login for "+credential.getUser());
            RabbitMQRPCMessageHandlerCredentialToken rabbitMQMessageHandler = new RabbitMQRPCMessageHandlerCredentialToken(EXCHANGE_NAME, PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY, PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY);
        	applicationContext.getAutowireCapableBeanFactory().autowireBean(rabbitMQMessageHandler);
        	rabbitMQMessageHandler.connect();
     	    Token result = rabbitMQMessageHandler.sendMessage(credential);
      	   	rabbitMQMessageHandler.close();
      	   	return result;
        } catch (Exception e) {
            logger.error("Fatal error sending data to EXCHANGE_NAME: "+EXCHANGE_NAME+", PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY:"+PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY+", PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY:"+PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY, e);
        }
        return null;
    }
}
