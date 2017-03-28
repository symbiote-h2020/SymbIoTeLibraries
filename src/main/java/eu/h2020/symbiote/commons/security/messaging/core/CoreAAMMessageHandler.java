package eu.h2020.symbiote.commons.security.messaging.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.h2020.symbiote.commons.security.messaging.restAAM.AAMMessageHandler;

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
