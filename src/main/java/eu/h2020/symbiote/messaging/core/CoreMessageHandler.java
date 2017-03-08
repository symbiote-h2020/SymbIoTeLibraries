package eu.h2020.symbiote.messaging.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;

@Component
public class CoreMessageHandler {
	private static Log logger = LogFactory.getLog(CoreMessageHandler.class);
    private static final String EXCHANGE_NAME = "symbIoTe.CoreAAM";
    private static final String COREAAM_LOGIN_TOKEN_ROUTING_KEY = "COREAAM_LOGIN_TOKEN";
    private static final String COREAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY = "HANDLER_LOGIN_TOKEN";

    
	@Autowired
	private ApplicationContext applicationContext;

    public Token login(Credential credential) {
        try {
            logger.info("Sending request of login for "+credential.getUser());
            RabbitMQRPCMessageHandlerCredentialToken rabbitMQMessageHandler = new RabbitMQRPCMessageHandlerCredentialToken(EXCHANGE_NAME, COREAAM_LOGIN_TOKEN_ROUTING_KEY, COREAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY);
        	applicationContext.getAutowireCapableBeanFactory().autowireBean(rabbitMQMessageHandler);
        	rabbitMQMessageHandler.connect();
     	    Token result = rabbitMQMessageHandler.sendMessage(credential);
      	   	rabbitMQMessageHandler.close();
      	   	return result;
        } catch (Exception e) {
            logger.error("Fatal error sending data to EXCHANGE_NAME: "+EXCHANGE_NAME+", COREAAM_LOGIN_TOKEN_ROUTING_KEY:"+COREAAM_LOGIN_TOKEN_ROUTING_KEY+", COREAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY:"+COREAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY, e);
        }
        return null;
    }
}
