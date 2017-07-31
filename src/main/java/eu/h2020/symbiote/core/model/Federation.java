package eu.h2020.symbiote.core.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author RuggenthalerC
 *
 *         Main object to represent one federation.
 */
public class Federation {

	public class FederationMember {
		@JsonProperty("id")
		private String id;

		@JsonProperty("interworkingService")
		private String interworkingService;

		public FederationMember(String id, String interworkingService) {
			this.id = id;
			this.interworkingService = interworkingService;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getInterworkingService() {
			return this.interworkingService;
		}

		public void setInterworkingService(String interworkingService) {
			this.interworkingService = interworkingService;
		}
	}

	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("public")
	private boolean isPublic = true;

	@JsonProperty("slaDefinition")
	private String slaDefinition;

	@JsonProperty("members")
	private List<FederationMember> members = new ArrayList<>();

	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public boolean isPublic() {
		return this.isPublic;
	}

	public String getSlaDefinition() {
		return this.slaDefinition;
	}

	public List<FederationMember> getMembers() {
		return this.members;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public void setSlaDefinition(String slaDefinition) {
		this.slaDefinition = slaDefinition;
	}

	public void setMembers(List<FederationMember> members) {
		this.members = members;
	}
}
