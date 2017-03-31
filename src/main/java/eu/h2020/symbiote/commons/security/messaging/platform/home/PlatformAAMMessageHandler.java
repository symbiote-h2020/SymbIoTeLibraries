package eu.h2020.symbiote.commons.security.messaging.platform.home;

import eu.h2020.symbiote.commons.security.constants.SHConstants;
import eu.h2020.symbiote.commons.security.messaging.bean.Credential;
import eu.h2020.symbiote.commons.security.messaging.bean.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PlatformAAMMessageHandler {
	private static Log logger = LogFactory.getLog(PlatformAAMMessageHandler.class);

    private String rabbitMQHostIP;

    public PlatformAAMMessageHandler (String rabbitMQHostIP) {
      this.rabbitMQHostIP = rabbitMQHostIP;

    }

    public Token login(Credential credential) {
        try {
            logger.info("Sending request of login for "+credential.getUser());
            RabbitMQRPCMessageHandlerCredentialToken rabbitMQMessageHandler = new RabbitMQRPCMessageHandlerCredentialToken(rabbitMQHostIP, SHConstants.EXCHANGE_NAME, SHConstants.HOME_PLATFORM_AAM_LOGIN_TOKEN_ROUTING_KEY, SHConstants.HOME_PLATOFRM_AAM_LOGIN_TOKEN_ROUTING_KEY_REPLY);
            rabbitMQMessageHandler.connect();
            Token result = rabbitMQMessageHandler.sendMessage(credential);
      	   	rabbitMQMessageHandler.close();
      	   	return result;
        } catch (Exception e) {
            logger.error("Fatal error sending data to EXCHANGE_NAME: "+SHConstants.EXCHANGE_NAME+", PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY:"+SHConstants.HOME_PLATFORM_AAM_LOGIN_TOKEN_ROUTING_KEY+", PLATOFRMAAM_LOGIN_TOKEN_ROUTING_KEY_REPLY:"+SHConstants.HOME_PLATOFRM_AAM_LOGIN_TOKEN_ROUTING_KEY_REPLY, e);
        }
        return null;
    }
}

