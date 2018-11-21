/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.semantics.mapping.model.Mapping;
import eu.h2020.symbiote.semantics.mapping.model.MappingRule;
import eu.h2020.symbiote.semantics.mapping.model.condition.ClassCondition;
import eu.h2020.symbiote.semantics.mapping.model.condition.DataPropertyTypeCondition;
import eu.h2020.symbiote.semantics.mapping.model.condition.UriClassCondition;
import eu.h2020.symbiote.semantics.mapping.model.production.ClassProduction;
import eu.h2020.symbiote.semantics.mapping.model.production.DataPropertyProduction;
import eu.h2020.symbiote.semantics.mapping.model.production.PropertyProduction;
import eu.h2020.symbiote.semantics.mapping.model.value.ReferenceValue;
import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class Mappings {

    private Mappings() {
    }

    public static Mapping mappingAtoB() {
        return new Mapping(
                new MappingRule(
                        new UriClassCondition(
                                ModelA.Person,
                                new DataPropertyTypeCondition(ModelA.name, XSDDatatype.XSDstring)),
                        new ClassProduction(ModelB.Person,
                                new DataPropertyProduction(
                                        ModelB.name,
                                        new ReferenceValue(ModelA.name)))));
    }
    
        public static Mapping mappingBtoA() {
        return new Mapping(
                new MappingRule(
                        new UriClassCondition(
                                ModelB.Person,
                                new DataPropertyTypeCondition(ModelB.name, XSDDatatype.XSDstring)),
                        new ClassProduction(ModelA.Person,
                                new DataPropertyProduction(
                                        ModelA.name,
                                        new ReferenceValue(ModelB.name)))));
    }
}
