package eu.h2020.symbiote.core.model.internal;

import eu.h2020.symbiote.core.model.RDFFormat;
import eu.h2020.symbiote.core.model.resources.Resource;

/**
 * Core layer internal representation of the resource. Extends the generic Resource description with RDF representation of the resource.
 *
 * Registry component is storing instances of this class and information about new resources in the system does also contain information in this form.
 *
 * Created by Mael on 28/03/2017.
 */
public class CoreResource extends Resource {

    private RDFFormat rdfFormat;
    private String rdf;

    public CoreResource() {
    }

    public RDFFormat getRdfFormat() {
        return rdfFormat;
    }

    public void setRdfFormat(RDFFormat rdfFormat) {
        this.rdfFormat = rdfFormat;
    }

    public String getRdf() {
        return rdf;
    }

    public void setRdf(String rdf) {
        this.rdf = rdf;
    }
}
