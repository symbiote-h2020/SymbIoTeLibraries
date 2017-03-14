package eu.h2020.symbiote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**! \class RegistrationHandlerApplication
 * \brief RegistrationHandlerApplication root class that has to be launched to run the RegistrationHandler with spring boot
 **/

/**
 * This class handles the initialization from the platform. Initially created by jose
 *
 * @author: jose, Elena Garrido
 * @version: 06/10/2016

 */
@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ComponentUsingSHApp {
/*
    public static <T> T createFeignClient(Class<T> client, String baseUrl) {
        return Feign.builder().
                encoder(new GsonEncoder()).decoder(new GsonDecoder()).
                target(client,baseUrl);
    }*/

	public static void main(String[] args)  {
		SpringApplication.run(ComponentUsingSHApp.class, args);
    }
	
	
}
