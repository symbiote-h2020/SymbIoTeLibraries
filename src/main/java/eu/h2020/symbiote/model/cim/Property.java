/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class Property {

    @JsonProperty("label")
    private final String label;
    @JsonProperty("comment")
    private final String comment;

    @JsonCreator
    public Property(@JsonProperty("label") String label,
            @JsonProperty("comment") String comment) {
        this.label = label;
        this.comment = comment;
    }

    public Property(Property p) {
        this.label = p.label;
        this.comment = p.comment;
    }

    public String getLabel() {
        return label;
    }

    public String getComment() {
        return comment;
    }

    // Helper
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Property:");
        buffer.append("label=").append(label).append(",");
        buffer.append("comment=").append(comment);

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

        if (!Objects.equals(this.label, op.label)) {
            return false;
        }

//    	if (!Objects.equals(this.comment, op.comment))	// Commented out. Comments should not play a role in "equals".
//    		return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.label);
    }

}
