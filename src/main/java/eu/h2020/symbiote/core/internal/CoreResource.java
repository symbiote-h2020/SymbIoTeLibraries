package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.security.accesspolicies.common.IAccessPolicySpecifier;

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
    private CoreResourceType type;
    private IAccessPolicySpecifier policySpecifier;

    public CoreResource() {
        // Empty constructor
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

    public CoreResourceType getType() {
        return type;
    }

    public void setType(CoreResourceType type) {
        this.type = type;
    }

    public IAccessPolicySpecifier getPolicySpecifier() {
        return policySpecifier;
    }

    public void setPolicySpecifier(IAccessPolicySpecifier policySpecifier) {
        this.policySpecifier = policySpecifier;
    }
}
