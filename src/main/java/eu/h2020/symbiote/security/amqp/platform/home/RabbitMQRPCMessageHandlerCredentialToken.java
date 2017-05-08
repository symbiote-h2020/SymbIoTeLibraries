package eu.h2020.symbiote.security.amqp.platform.home;

import eu.h2020.symbiote.security.amqp.GenericRabbitMQRPCMessageHandler;
import eu.h2020.symbiote.security.payloads.Credentials;
import eu.h2020.symbiote.security.token.Token;


/**
 * * This class is to communicate with RabbitMQ. Initially created by Elena
 *
 * @author Elena Garrido
 * @version 06/03/2017
 *          \class RabbitMQRPCMessageHandlerCredentialToken
 *          \brief This class extends from the \class GenericRabbitMQRPCMessageHandler and will be able to write and read a \class Credential
 *          from the rabbitMQ RPC queues an receive back a \class Token
 **/
class RabbitMQRPCMessageHandlerCredentialToken extends GenericRabbitMQRPCMessageHandler<Credentials, Token> {

    public RabbitMQRPCMessageHandlerCredentialToken(String rabbitMQHostIP, String exchangeName, String requestQueueName, String replyQueueName) {
        super(rabbitMQHostIP, exchangeName, requestQueueName, replyQueueName, Token.class);
    }
}

