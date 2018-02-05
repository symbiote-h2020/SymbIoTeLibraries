package eu.h2020.symbiote.model.mim;

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
	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("public")
	private boolean isPublic = true;

	@JsonProperty("informationModel")
	private InformationModel informationModel;

	@JsonProperty("slaConstraints")
	private List<QoSConstraint> slaConstraints;

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

	public List<QoSConstraint> getSlaConstraints() {
		return this.slaConstraints;
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

	public void setSlaConstraints(List<QoSConstraint> slaConstraints) {
		this.slaConstraints = slaConstraints;
	}

	public void setMembers(List<FederationMember> members) {
		this.members = members;
	}

	public InformationModel getInformationModel() {
		return this.informationModel;
	}

	public void setInformationModel(InformationModel informationModel) {
		this.informationModel = informationModel;
	}
}
