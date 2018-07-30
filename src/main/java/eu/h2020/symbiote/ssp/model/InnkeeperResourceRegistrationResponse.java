package eu.h2020.symbiote.ssp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InnkeeperResourceRegistrationResponse {

	private static Log log = LogFactory.getLog(InnkeeperResourceRegistrationResponse.class);


	@JsonProperty("symIdResource")
	private String symIdResource;

	@JsonProperty("sspIdResource")
	private String sspIdResource;

	@JsonProperty("symId")
	private String symId;

	@JsonProperty("sspId")
	private String sspId;

	@JsonProperty("result")
	private String result;

	@JsonProperty("registrationExpiration")
	private Integer registration_expiration;

	public InnkeeperResourceRegistrationResponse() {}

	public InnkeeperResourceRegistrationResponse(String SymIdResource, String sspIdResource, String symId,String sspId, String result) {
		this.symIdResource=SymIdResource;
		this.sspIdResource=sspIdResource;
		this.symId=symId;
		this.sspId=sspId;
		this.result=result;
	}
	public InnkeeperResourceRegistrationResponse(String SymIdResource, String sspIdResource, String symId,String sspId, String result, Integer registration_expiration) {
		this.symIdResource=SymIdResource;
		this.sspIdResource=sspIdResource;
		this.symId=symId;
		this.sspId=sspId;
		this.result=result;
		this.registration_expiration=registration_expiration;
	}

	public String getSspIdResource() {
		return this.sspIdResource;
	}

	public void setSspIdResource(String sspIdResource) {
		this.sspIdResource=sspIdResource;
	}

	public String getSymId() {
		return this.symId;
	}

	public void setSymIdDEV(String symId) {
		this.symId=symId;
	}

	public String getResult() {
		return this.result;
	}

	public int getRegistrationExpiration() {
		return this.registration_expiration;
	}
}
