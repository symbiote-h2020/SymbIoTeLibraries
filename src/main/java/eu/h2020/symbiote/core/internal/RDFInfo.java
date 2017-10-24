package eu.h2020.symbiote.core.internal;

/**
 * Respresents an object that has RDF attached.
 *
 * Created by Szymon Mueller on 15/03/2017.
 */
public class RDFInfo {

    /**
     * String representing RDF description.
     */
    private String rdf;

    /**
     * Format of the RDF.
     */
    private RDFFormat rdfFormat;

    public String getRdf() {
        return rdf;
    }

    public void setRdf(String rdf) {
        this.rdf = rdf;
    }

    public RDFFormat getRdfFormat() {
        return rdfFormat;
    }

    public void setRdfFormat(RDFFormat rdfFormat) {
        this.rdfFormat = rdfFormat;
    }
}
