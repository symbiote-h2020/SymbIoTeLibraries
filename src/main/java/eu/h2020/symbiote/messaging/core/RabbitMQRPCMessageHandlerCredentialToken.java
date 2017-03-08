package eu.h2020.symbiote.messaging.core;

import eu.h2020.symbiote.messaging.bean.Credential;
import eu.h2020.symbiote.messaging.bean.Token;
import eu.h2020.symbiote.messaging.rabbitmq.GenericRabbitMQRPCMessageHandler;


/**
 * This class is to communicate with RabbitMQ. Initially created by Elena
 *
 * @author: Elena Garrido
 * @version: 06/03/2017

 */
/**! \class RabbitMQRPCMessageHandlerCredentialToken
 * \brief This class extends from the \class GenericRabbitMQRPCMessageHandler and will be able to write and read a \class Credential
 * from the rabbitMQ RPC queues an receive back a \class Token
 **/
class RabbitMQRPCMessageHandlerCredentialToken extends GenericRabbitMQRPCMessageHandler<Credential,Token> {

    public RabbitMQRPCMessageHandlerCredentialToken(String excchangeName, String requestQueueName, String replyQueueName) {
		super(excchangeName, requestQueueName, replyQueueName, Token.class);
	}
}
