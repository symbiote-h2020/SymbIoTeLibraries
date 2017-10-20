package eu.h2020.symbiote.core.internal;

/**
 * Enum containing list of understandable RDF formats.
 *
 * @author jab
 */
public enum RDFFormat {
    Turtle("TURTLE"),
    NTriples("NTRIPLES"),
    RDFXML("RDFXML"),
    N3("N3"),
    JSONLD("JSONLD");

    private final String name;

    RDFFormat(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static RDFFormat fromFilenameExtension(String filenameExtension) {
        switch (filenameExtension) {
            case "ttl":
                return RDFFormat.Turtle;
            case "nt":
                return RDFFormat.NTriples;
            case "rdf":
            case "xml":
                return RDFFormat.RDFXML;
            case "n3":
                return RDFFormat.N3;
            case "jsonld":
                return RDFFormat.JSONLD;
            default:
                throw new IllegalArgumentException("unknown file extension '" + filenameExtension + "'");
        }
    }
}
