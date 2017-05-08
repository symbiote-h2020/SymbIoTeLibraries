package eu.h2020.symbiote.security.amqp.core;

import eu.h2020.symbiote.security.rest.AAMMessageHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CoreAAMMessageHandler  extends AAMMessageHandler {
	private static final Log logger = LogFactory.getLog(CoreAAMMessageHandler .class);
	
	private String coreAAMUrl;
	
	public CoreAAMMessageHandler(String coreAAMUrl) {
		this.coreAAMUrl = coreAAMUrl;
		createClient();
	}

	public void createClient() {
		logger.info("coreAAMUrl "+coreAAMUrl);
		createClient(coreAAMUrl);
	}
}

