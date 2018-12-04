package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.jsonld.JsonLDType;
import eu.h2020.symbiote.jsonld.JsonLDVocab;
import java.util.Objects;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
@JsonLDType(ModelB.Person)
@JsonLDVocab
public class PersonB {

    public PersonB() {

    }

    public PersonB(String name) {
        this.theName = name;
    }

    private String theName;

    public String getTheName() {
        return theName;
    }

    public void setTheName(String theName) {
        this.theName = theName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.theName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonB other = (PersonB) obj;
        if (!Objects.equals(this.theName, other.theName)) {
            return false;
        }
        return true;
    }

}
