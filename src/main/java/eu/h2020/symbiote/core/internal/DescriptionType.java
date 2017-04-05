package eu.h2020.symbiote.core.internal;

/**
 * Enum holding list of possible description types: (RDF, JSON)
 *
 * Created by Szymon Mueller on 31/03/2017.
 */
public enum DescriptionType {

    RDF("rdf"),
    BASIC("basic");

    private final String name;

    DescriptionType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
