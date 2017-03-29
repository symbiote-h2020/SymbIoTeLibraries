package eu.h2020.symbiote;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import eu.h2020.symbiote.commons.security.constants.SHConstants;
import eu.h2020.symbiote.commons.security.messaging.bean.Credential;
import eu.h2020.symbiote.commons.security.messaging.bean.Token;

@Service
public class PlatformAAMDummyServer {
    private static Log logger = LogFactory.getLog(PlatformAAMDummyServer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

	   /**
	   * Spring AMQP Listener for resource registration requests. This method is invoked when Registration
	   * Handler sends a resource registration request and it is responsible for forwarding the message
	   * to the symbIoTe core. As soon as it receives a reply, it manually sends back the response
	   * to the Registration Handler via the appropriate message queue by the use of the RestAPICallback.
	   * 
	   * @param headers The AMQP headers
	   */
	    @RabbitListener(bindings = @QueueBinding(
	        value = @Queue(value = SHConstants.HOME_PLATFORM_AAM_LOGIN_TOKEN_ROUTING_KEY, durable = "true", autoDelete = "false", exclusive = "false"),
	        exchange = @Exchange(value = SHConstants.EXCHANGE_NAME, ignoreDeclarationExceptions = "true"),
	        key = SHConstants.HOME_PLATFORM_AAM_LOGIN_TOKEN_ROUTING_KEY)
	    )
	    public void resourceRegistration(Message message, @Headers() Map<String, String> headers) {
	    	logger.info("resourceRegistration"+new String(message.getBody()));
            Gson gson = new Gson();
            Credential credential = gson.fromJson(new String(message.getBody()),  Credential.class);
            logger.info("User trying to login "+credential.getUser()+ " - "+credential.getPasswd());

            
      	  Token token = new Token();
    	  token.setToken("eyJhbGciOiJSUzUxMiJ9.eyJzdWIiOiJ0ZXN0MSIsImV4cCI6MTQ5MDI3ODIyMSwibmFtZSI6InRlc3QyIn0.V2qYTXOp1Xv1jSXZaxn-pbr_Byhmhuu6fAMy0fytco1JgJpvxTw5wlhJ1GuAvuA71IRmINyCAgcUo4oBrXFd4Wy_NthR3pQ5YIflD2t31RoVD1QQlhARri6A-mkjj4rVbsU98BG3ixvdYTkAjiLUbpvNrqm2Y3cDstaLWcSfGzN7ulVuMbEUWbZj9rkW_G4VF62vvOXL9C8UsxYyV0qx9dPzy2iiMGJQ-s16dYb5jiFY5BfvxUf3TWRJPhe5eaX5X7oDvzNh4JDWAFxoKYEH2PvoHctknX5Kon0HBCV_8xmJtxwlKB3lzeugqqFQW8HQiAqSbTAhkcmK9QGs_zkmyA");
            String response = gson.toJson(token);
             
	        rabbitTemplate.convertAndSend(headers.get("amqp_replyTo"), response.getBytes(),
               m -> {
                		Object a = headers.get("amqp_correlationId");
                        // XXX not sure why the correlationIdString is empty and forces us to use deprecated API
                        m.getMessageProperties().setCorrelationId((byte[])a);
                        return m;
               });
	    }
	    
	    
}

