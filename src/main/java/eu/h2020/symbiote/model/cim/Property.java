/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author <a href="mailto:aleksandar.antonic@fer.hr">Aleksandar Antonic</a>
 */
public class Property {

    @JsonProperty("name")
    private final String name;
    @JsonProperty("iri")
    private final String iri;
    @JsonProperty("description")
    private final List<String> description;

    @JsonCreator
    @PersistenceConstructor
    public Property(@JsonProperty("name") String name,
                    @JsonProperty("iri") String iri,
                    @JsonProperty("description") List<String> description) {
        this.name = name;
        this.iri = iri;
        this.description = description;
    }

    public Property(Property p) {
        this.name = p.getName();
        this.iri = p.getIri();
        this.description = p.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getIri() { return iri; }

    public List<String> getDescription() {
        return description;
    }

    // Helper
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("iri=").append(iri).append(",");
        buffer.append("Property:");
        buffer.append("name=").append(name).append(",");
        buffer.append("description=").append(description);

        return buffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Property)) {
            return false;
        }

        Property op = (Property) o;

        if (!Objects.equals(this.iri, op.iri)) {
            return false;
        }

//    	if (!Objects.equals(this.comment, op.comment))	// Commented out. Comments should not play a role in "equals".
//    		return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.iri);
    }

}
