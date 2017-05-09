package eu.h2020.symbiote.security.amqp.platform.home;

import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PlatformAAMMessageHandler {
    private static Log logger = LogFactory.getLog(PlatformAAMMessageHandler.class);

    private String rabbitMQHostIP;

    public PlatformAAMMessageHandler(String rabbitMQHostIP) {
        this.rabbitMQHostIP = rabbitMQHostIP;

    }

    public Token login(Credentials credentials) throws SecurityHandlerException {
        try {
            logger.info("Sending request of login for " + credentials.getUsername());
            RabbitMQRPCMessageHandlerCredentialToken rabbitMQMessageHandler =
                    new RabbitMQRPCMessageHandlerCredentialToken(
                            rabbitMQHostIP,
                            SecurityHandlerConstants.EXCHANGE_NAME,
                            SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_QUEUE,
                            SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_ROUTING_KEY);
            rabbitMQMessageHandler.connect();
            Token result = rabbitMQMessageHandler.sendMessage(credentials);
            rabbitMQMessageHandler.close();
            return result;
        } catch (Exception e) {
            String message = "Fatal error sending data to EXCHANGE_NAME: "
                    + SecurityHandlerConstants.EXCHANGE_NAME + ", PLATFORM_AAM_LOGIN_QUEUE:"
                    + SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_QUEUE + ", PLATFORM_AAM_LOGIN_ROUTING_KEY:"
                    + SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_ROUTING_KEY;
            logger.error(message, e);
            throw new SecurityHandlerException(message, e);
        }
    }
}
