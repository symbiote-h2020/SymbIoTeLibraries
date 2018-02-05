package eu.h2020.symbiote.unit.core.model;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eu.h2020.symbiote.model.mim.Comparator;
import eu.h2020.symbiote.model.mim.Federation;
import eu.h2020.symbiote.model.mim.FederationMember;
import eu.h2020.symbiote.model.mim.QoSConstraint;
import eu.h2020.symbiote.model.mim.QoSMetric;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class FederationTest {

	@Test
	public void federationSerialTest() throws Exception {

		Federation fed = new Federation();
		fed.setId("fedId");
		fed.setName("fedName");
		fed.setPublic(false);
		
		List<QoSConstraint> constraints = new ArrayList<>();
		
		QoSConstraint availability = new QoSConstraint();
		availability.setMetric(QoSMetric.availability);
		availability.setComparator(Comparator.greaterThanOrEqual);
		availability.setThreshold(90.0);
		constraints.add(availability);
		
		fed.setSlaConstraints(constraints);

		List<FederationMember> members = new ArrayList<FederationMember>();
		members.add(new FederationMember("123", "/url/123"));
		members.add(new FederationMember("456", "/url/456"));

		fed.setMembers(members);

		String serialFed = convertObjectToJson(fed);

		Assert.assertTrue(serialFed.startsWith("{"));
		Assert.assertTrue(serialFed.endsWith("}"));
		Assert.assertTrue(serialFed.contains("\"id\":" + "\"" + fed.getId() + "\""));
		Assert.assertTrue(serialFed.contains("\"name\":" + "\"" + fed.getName() + "\""));
		Assert.assertTrue(serialFed.contains("\"slaConstraints\":"));
		Assert.assertTrue(serialFed.contains("\"public\":" + fed.isPublic()));

		fed.getMembers().forEach(member -> {
			Assert.assertTrue(serialFed
					.contains("\"platformId\":\"" + member.getPlatformId() + "\",\"interworkingServiceURL\":\"" + member.getInterworkingServiceURL() + "\"}"));
		});
		
		fed.getSlaConstraints().forEach(constraint -> {
			Assert.assertTrue(serialFed.contains("\"metric\":\"" + constraint.getMetric().toString() + "\""));
			Assert.assertTrue(serialFed.contains("\"comparator\":\"" + constraint.getComparator().toString() + "\""));
			Assert.assertTrue(serialFed.contains("\"threshold\":" + constraint.getThreshold()));
		});
		
		Assert.assertEquals(2, fed.getMembers().size());
	}

	private String convertObjectToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		mapper.setSerializationInclusion(Include.NON_EMPTY);
		return mapper.writeValueAsString(obj);
	}
}