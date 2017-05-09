package eu.h2020.symbiote.security.amqp.platform.home;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import eu.h2020.symbiote.security.constants.SecurityHandlerConstants;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PlatformAAMMessageHandler {
    private static Log log = LogFactory.getLog(PlatformAAMMessageHandler.class);

    private final ConnectionFactory factory = new ConnectionFactory();
    private final ObjectMapper mapper = new ObjectMapper();

    public PlatformAAMMessageHandler(String rabbitMQHostIP, String rabbitMQUsername, String rabbitMQPassword) {
        factory.setHost(rabbitMQHostIP);
        factory.setUsername(rabbitMQUsername);
        factory.setPassword(rabbitMQPassword);
    }

    public Token login(Credentials credentials) throws SecurityHandlerException {
        try {
            log.info("Sending request of login for " + credentials.getUsername());

            RpcClient client = new RpcClient(factory.newConnection().createChannel(), "", SecurityHandlerConstants
                    .HOME_PLATFORM_AAM_LOGIN_QUEUE, 5000);

            byte[] response = client.primitiveCall(mapper.writeValueAsString(new Credentials("user", "password"))
                    .getBytes());

            return mapper.readValue(response, Token.class);
        } catch (Exception e) {
            String message = "Fatal error sending data to EXCHANGE_NAME: "
                    + SecurityHandlerConstants.EXCHANGE_NAME + ", PLATFORM_AAM_LOGIN_QUEUE:"
                    + SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_QUEUE + ", PLATFORM_AAM_LOGIN_ROUTING_KEY:"
                    + SecurityHandlerConstants.HOME_PLATFORM_AAM_LOGIN_ROUTING_KEY;
            log.error(message, e);
            throw new SecurityHandlerException(message, e);
        }
    }
}
