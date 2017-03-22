package eu.h2020.symbiote.messaging.core;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import eu.h2020.symbiote.messaging.restAAM.AAMMessageHandler;

@Component
@EnableAutoConfiguration
public class CoreAAMMessageHandler  extends AAMMessageHandler {
	private static final Log logger = LogFactory.getLog(CoreAAMMessageHandler .class);
	@Value("${symbiote.coreaam.url}")
	String coreAAMUrl;
	
	@PostConstruct
	public void createClient() {
		logger.info("coreAAMUrl "+coreAAMUrl);
		createClient(coreAAMUrl);
	}
}
