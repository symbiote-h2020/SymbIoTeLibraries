package eu.h2020.symbiote.sh.messaging.rabbitmq;

/**
 * Created by Elena Garrido on 18/01/2017.
 */
public interface RabbitMQMessageReceptionListener<T> {
    void onReceivedMessage(T object);
}
