package eu.h2020.symbiote.client;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class SparqlQueries {

    private SparqlQueries() {
    }

    public static final String META_GRAPH = "http://www.symbiote-h2020.eu/ontology/internal/meta";
    public static final String SPARQL_PREFIXES
            = "PREFIX core: <http://www.symbiote-h2020.eu/ontology/core#> \n"
            + "PREFIX meta: <http://www.symbiote-h2020.eu/ontology/meta#> \n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n\n";

    public static final String getMapping(String sourceId, String targetId) {
        return SPARQL_PREFIXES
                + "SELECT DISTINCT ?definition\n"
                + "FROM <" + META_GRAPH + ">"
                + "WHERE {\n"
                + "	?mapping a meta:Mapping;\n"
                + "		meta:hasDefinition ?definition;\n"
                + "		meta:hasSourceModel [ \n"
                + "			a meta:InformationModel ;\n"
                + "			core:id \"" + sourceId + "\" .\n"
                + "		];\n"
                + "		meta:hasDestinationModel [ \n"
                + "			a meta:InformationModel ;\n"
                + "			core:id \"" + targetId + "\" .\n"
                + "		];		\n"
                + "}";
    }

    public static final String getPIMIdByPlatformId(String platformId) {
        return SPARQL_PREFIXES
                + "SELECT DISTINCT ?id\n"
                + "FROM <" + META_GRAPH + ">\n"
                + "WHERE {\n"
                + "	?platform a/rdfs:subClassOf* meta:System;\n"
                + "		core:id \"" + platformId + "\";\n"
                + "		meta:hasService [\n"
                + "			a meta:InterworkingService;\n"
                + "			meta:usesInformationModel [\n"
                + "				a meta:InformationModel;\n"
                + "				core:id ?id\n"
                + "			]\n"
                + "		]\n"
                + "}";
    }

    public static final String getPIMIdByResourceId(String resourceId) {
        return SPARQL_PREFIXES
                + "SELECT DISTINCT ?id\n"
                + "FROM <" + META_GRAPH + ">\n"
                + "WHERE {\n"
                + "	?service a meta:InterworkingService; \n"
                + "		meta:hasResource [ \n"
                // will not work if resource is instance of class that has another superclass in PIM because we can't load PIM at that moment
                // e.g. resource a ?x. ?x rdfs:subClass ?y. ?x rdfs:subClass* core:Resource.   with ?x and ?y being classes in PIM
                // if PIM does not contain a subclass hierarchy of core:Resource itself it would work, but symbIoTe is not limited to that
                //+ "			a/rdfs:subClassOf* core:Resource; \n"   
                + "			core:id \"" + resourceId + "\" \n"
                + "		];\n"
                + "		meta:usesInformationModel [ \n"
                + "			a meta:InformationModel; \n"
                + "			core:id ?id \n"
                + "		]\n"
                + "}";
    }

    public static final String getPIMIdByTypes(Set<String> types) {
        return SPARQL_PREFIXES
                + "SELECT DISTINCT ?id\n"
                + "WHERE {\n"
                + "	GRAPH ?graph {\n"
                + "		VALUES ?class { " + types.stream().collect(Collectors.joining(", ", "<", ">")) + " }\n"
                + "		?class a owl:Class .\n"
                + "	}\n"
                + "     GRAPH <" + META_GRAPH + "> {\n"
                + "     	?model a meta:InformationModel ;\n"
                + "     	meta:hasDefinition ?graph ;\n"
                + "     	core:id ?id .\n"
                + "     }\n"
                + "}\n"
                + "GROUP BY ?id\n"
                + "HAVING (COUNT(DISTINCT ?class) = " + types.size() + ")";
    }

}
