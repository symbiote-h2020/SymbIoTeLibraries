package eu.h2020.symbiote.security.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.RpcClient;
import eu.h2020.symbiote.security.constants.AAMConstants;
import eu.h2020.symbiote.security.exceptions.SecurityHandlerException;
import eu.h2020.symbiote.security.payloads.CheckRevocationResponse;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Client used to access local/intenal AAM over AMQP by Symbiote components
 *
 * @author Mikołaj Dobski (PSNC)
 */
public class LocalAAMOverAMQPClient {
    private static Log log = LogFactory.getLog(LocalAAMOverAMQPClient.class);

    private final ConnectionFactory factory = new ConnectionFactory();
    private final ObjectMapper mapper = new ObjectMapper();

    public LocalAAMOverAMQPClient(String rabbitMQHostIP, String rabbitMQUsername, String rabbitMQPassword) {
        factory.setHost(rabbitMQHostIP);
        factory.setUsername(rabbitMQUsername);
        factory.setPassword(rabbitMQPassword);
    }

    public Token login(Credentials credentials) throws SecurityHandlerException {
        try {
            log.debug("Sending request of login for " + credentials.getUsername());

            RpcClient client = new RpcClient(factory.newConnection().createChannel(), "", AAMConstants
                    .AAM_LOGIN_QUEUE, 5000);

            byte[] response = client.primitiveCall(mapper.writeValueAsString(credentials)
                    .getBytes());

            return mapper.readValue(response, Token.class);
        } catch (Exception e) {
            log.error(e);
            throw new SecurityHandlerException(e.getMessage(), e);
        }
    }

    public CheckRevocationResponse checkHomeTokenRevocation(Token token) throws SecurityHandlerException {
        try {
            RpcClient client = new RpcClient(factory.newConnection().createChannel(), "", AAMConstants
                    .AAM_CHECK_REVOCATION_QUEUE, 5000);
            byte[] amqpResponse = client.primitiveCall(mapper.writeValueAsString(token).getBytes());

            return mapper.readValue(amqpResponse,
                    CheckRevocationResponse.class);
        } catch (Exception e) {
            log.error(e);
            throw new SecurityHandlerException(e.getMessage(), e);
        }
    }
}
