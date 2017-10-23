/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class Property {

    @JsonProperty("name")
    private final String name;
    @JsonProperty("description")
    private final List<String> description;

    @JsonCreator
    public Property(@JsonProperty("name") String name,
            @JsonProperty("description") List<String> description) {
        this.name = name;
        this.description = description;
    }

    public Property(Property p) {
        this.name = p.getName();
        this.description = p.getDescription();
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    // Helper
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

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

        if (!Objects.equals(this.name, op.name)) {
            return false;
        }

//    	if (!Objects.equals(this.comment, op.comment))	// Commented out. Comments should not play a role in "equals".
//    		return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

}
