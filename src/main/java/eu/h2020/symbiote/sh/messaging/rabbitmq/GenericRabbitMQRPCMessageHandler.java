package eu.h2020.symbiote.sh.messaging.rabbitmq;

import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;


/**
 * This class is to communicate with RabbitMQ. Initially created by Elena
 *
 * @author: Elena Garrido
 * @version: 18/01/2017

 */
public class GenericRabbitMQRPCMessageHandler <T,O> {

    private static Log logger = LogFactory.getLog( GenericRabbitMQRPCMessageHandler.class );

    @Value("${symbiote.rabbitmq.host.ip}")
    String rabbitMQHostIP;

    String exchangeName = "";

    private Connection connection;
    private Channel channel;
    private QueueingConsumer consumer;
    String replyQueueName;
    String requestQueueName;
    Type type;
    
    public GenericRabbitMQRPCMessageHandler(String exchangeName, String requestQueueName, String replyQueueName, Type type) {
    	logger.info("Creating with requestQueueName:"+requestQueueName+" and replyQueueName:" +replyQueueName);
    	this.replyQueueName = replyQueueName;
    	this.requestQueueName = requestQueueName;
    	this.type= type;
    	this.exchangeName = exchangeName;
    }
    
    public void connect() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMQHostIP);
        connection = factory.newConnection();
        channel = connection.createChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);    	
    }

    /**
     * Method for sending a message to specified 'queue' on RabbitMQ server. Object is converted to Json.
     *
     * @param queueName
     * @param object
     * @throws Exception
     */

     @SuppressWarnings("unchecked")
	public O sendMessage(T object) throws Exception {
    	 O result = null;
         String corrId = java.util.UUID.randomUUID().toString();
         Gson gson = new Gson();
         String objectInJson = gson.toJson(object);
         
         BasicProperties props = new BasicProperties
                 .Builder()
                 .correlationId(corrId)
                 .contentType("application/json")
                 .replyTo(replyQueueName)
                 .build();
         
         channel.basicPublish(exchangeName, requestQueueName, props, objectInJson.getBytes());

         while (true) {
             QueueingConsumer.Delivery delivery = consumer.nextDelivery();
             if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                 String response = new String(delivery.getBody());
                 logger.info("Received reply: " + response);
                 result = (O)gson.fromJson(response, type);
                 logger.info("Result "+result);
                 break;
             }
         }

         return result;

     }

     public void close() throws Exception {
         connection.close();
     }
    
}
