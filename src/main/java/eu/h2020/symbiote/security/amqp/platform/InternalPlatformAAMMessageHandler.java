package eu.h2020.symbiote.security.amqp.platform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class InternalPlatformAAMMessageHandler {
    private static Log log = LogFactory.getLog(InternalPlatformAAMMessageHandler.class);

    private final ConnectionFactory factory = new ConnectionFactory();
    private final ObjectMapper mapper = new ObjectMapper();

    public InternalPlatformAAMMessageHandler(String rabbitMQHostIP, String rabbitMQUsername, String rabbitMQPassword) {
        factory.setHost(rabbitMQHostIP);
        factory.setUsername(rabbitMQUsername);
        factory.setPassword(rabbitMQPassword);
    }

    public Token login(Credentials credentials) throws SecurityHandlerException {
        try {
            log.info("Sending request of login for " + credentials.getUsername());

            RpcClient client = new RpcClient(factory.newConnection().createChannel(), "", AAMConstants
                    .AAM_LOGIN_QUEUE, 5000);

            byte[] response = client.primitiveCall(mapper.writeValueAsString(new Credentials("user", "password"))
                    .getBytes());

            return mapper.readValue(response, Token.class);
        } catch (Exception e) {
            String message = "Fatal error sending data to AAM_EXCHANGE_NAME: "
                    + AAMConstants.AAM_EXCHANGE_NAME + ", PLATFORM_AAM_LOGIN_QUEUE:"
                    + AAMConstants.AAM_LOGIN_QUEUE + ", PLATFORM_AAM_LOGIN_ROUTING_KEY:"
                    + AAMConstants.AAM_LOGIN_ROUTING_KEY;
            log.error(message, e);
            throw new SecurityHandlerException(message, e);
        }
    }
}
