package eu.h2020.symbiote.model.mim;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author RuggenthalerC
 *
 *         Main object to represent one federation.
 */
public class Federation {

	@Id
	@JsonProperty("id")
    @Pattern(regexp="^[\\w-]{4,}$")
    @Size(max=30)
    @NotNull
	private String id;

	@JsonProperty("name")
    @Size(min=3, max=30)
    @NotNull
    private String name;

	@JsonProperty("public")
    @NotNull
	private Boolean isPublic = true;

	@JsonProperty("informationModel")
    @Valid
    @NotNull
	private InformationModel informationModel;

	@JsonProperty("slaConstraints")
    @Valid
    @NotNull
	private List<QoSConstraint> slaConstraints;

	@JsonProperty("members")
    @Valid
    @NotNull
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
