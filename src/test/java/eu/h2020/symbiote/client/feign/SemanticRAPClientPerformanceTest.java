/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Sets;
import eu.h2020.symbiote.client.AbstractSemanticRAPClient;
import eu.h2020.symbiote.client.SparqlQueries;
import static eu.h2020.symbiote.client.feign.SemanticRAPClientTest.RESOURCE_ID_SERVICE_A;
import eu.h2020.symbiote.client.interfaces.SemanticRAPClient;
import eu.h2020.symbiote.core.internal.RDFFormat;
import eu.h2020.symbiote.jsonld.JsonLDHelper;
import eu.h2020.symbiote.jsonld.JsonLDObjectMapper;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.semantics.ModelHelper;
import eu.h2020.symbiote.semantics.mapping.data.DataMapper;
import eu.h2020.symbiote.semantics.mapping.model.Mapping;
import eu.h2020.symbiote.semantics.mapping.model.UnsupportedMappingException;
import eu.h2020.symbiote.semantics.mapping.parser.ParseException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.rdf.model.Model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class SemanticRAPClientPerformanceTest extends SemanticRAPClientTest {

    private static final Log LOG = LogFactory.getLog(SemanticRAPClientPerformanceTest.class);
    private static final int TEST_SIZE = 50;
    private static final long SCALING_FACTOR_ONE_RUN = 1000;
    private static final long SCALING_FACTOR_BATCH = 1000;
    protected static final String MAPPING_IOSB_TO_KIT_FILE = "IOSB to KIT.map";
    protected static final String MAPPING_KIT_TO_IOSB_FILE = "KIT to IOSB.map";

    @Test
    public void testSimple_Classes() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        for (int i = 0; i < TEST_SIZE; i++) {
            testInvokeServiceWithMapping_Success();
        }
    }

    @Test
    public void testEduCampus_String() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        for (int i = 0; i < TEST_SIZE; i++) {
            testInvokeServiceWithEducampusMapping_Success();
        }
    }

    private void testInvokeServiceWithEducampusMapping_Success() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        // A = IOSB
        // B = KIT
        FeignRAPClient semanticRapClient = createSemanticRapClient();
        String name = UUID.randomUUID().toString();
        String personAJson = "[\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#BleBeacon\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#Room\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#airConditioning\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#beacon1-iosb\",\n"
                + "		\"@type\": [\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#BleBeacon\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#beaconId\": [\n"
                + "			{\n"
                + "				\"@value\": \"beacon1-iosb-beaconId\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#minor\": [\n"
                + "			{\n"
                + "				\"@value\": 1\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#major\": [\n"
                + "			{\n"
                + "				\"@value\": 2\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#projector\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#room1-iosb\",\n"
                + "		\"@type\": [\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#Room\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"room1-iosb-name\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#description\": [\n"
                + "			{\n"
                + "				\"@value\": \"room1-iosb-description\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#capacity\": [\n"
                + "			{\n"
                + "				\"@value\": 42\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#roomNo\": [\n"
                + "			{\n"
                + "				\"@value\": \"room1-iosb-roomNo\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#hasFeature\": [\n"
                + "			{\n"
                + "				\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#airConditioning\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#whiteboard\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#projector\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#room2-iosb\",\n"
                + "		\"@type\": [\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#Room\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"room2-iosb-name\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#description\": [\n"
                + "			{\n"
                + "				\"@value\": \"room2-iosb-description\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#capacity\": [\n"
                + "			{\n"
                + "				\"@value\": 17\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://iosb.fraunhofer.de/ilt/ontologies/educampus#roomNo\": [\n"
                + "			{\n"
                + "				\"@value\": \"room2-iosb-roomNo\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://iosb.fraunhofer.de/ilt/ontologies/educampus#whiteboard\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://www.w3.org/2002/07/owl#NamedIndividual\"\n"
                + "	}\n"
                + "]";
        String personBJson = "[\n"
                + "	{\n"
                + "		\"@id\": \"_:b0\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#SeatingCapability\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#capacity\": [\n"
                + "			{\n"
                + "				\"@value\": 17\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"_:b1\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Wallplug\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"_:b2\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Ethernet\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"_:b3\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#AirConditioning\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#AirConditioning\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Area\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Beacon\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Building\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Ethernet\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Floor\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#LectureHall\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Office\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#SeatingCapability\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#Wallplug\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#area1-kit\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Office\", \"http://cm.kit.edu/smartcampus/pim#Area\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"area1-kit-name\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#description\": [\n"
                + "			{\n"
                + "				\"@value\": \"area1-kit-description\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#roomNo\": [\n"
                + "			{\n"
                + "				\"@value\": \"area1-kit-roomNo\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#hasFeature\": [\n"
                + "			{\n"
                + "				\"@id\": \"_:b0\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#area2-kit\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#LectureHall\", \"http://cm.kit.edu/smartcampus/pim#Area\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"area2-kit-name\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#description\": [\n"
                + "			{\n"
                + "				\"@value\": \"area2-kit-description\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#roomNo\": [\n"
                + "			{\n"
                + "				\"@value\": \"area2-kit-roomNo\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#hasFeature\": [\n"
                + "			{\n"
                + "				\"@id\": \"_:b1\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"@id\": \"_:b2\"\n"
                + "			},\n"
                + "			{\n"
                + "				\"@id\": \"_:b3\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#beacon1-kit\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Beacon\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#uuid\": [\n"
                + "			{\n"
                + "				\"@value\": \"beacon1-kit-uuid\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#minor\": [\n"
                + "			{\n"
                + "				\"@value\": 11\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#major\": [\n"
                + "			{\n"
                + "				\"@value\": 22\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#building1-kit\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Building\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"building1-kit-name\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://cm.kit.edu/smartcampus/pim#floor1-kit\",\n"
                + "		\"@type\": [\"http://cm.kit.edu/smartcampus/pim#Floor\", \"http://www.w3.org/2002/07/owl#NamedIndividual\"],\n"
                + "		\"http://www.symbiote-h2020.eu/ontology/core#name\": [\n"
                + "			{\n"
                + "				\"@value\": \"floor1-kit-name\"\n"
                + "			}\n"
                + "		],\n"
                + "		\"http://cm.kit.edu/smartcampus/pim#isInBuilding\": [\n"
                + "			{\n"
                + "				\"@id\": \"http://cm.kit.edu/smartcampus/pim#building1-kit\"\n"
                + "			}\n"
                + "		]\n"
                + "	},\n"
                + "	{\n"
                + "		\"@id\": \"http://www.w3.org/2002/07/owl#NamedIndividual\"\n"
                + "	}\n"
                + "]";
        // mock RAP
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_A, personAJson);
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);
        // mock PIM ID by types
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);

        // mock mapping
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile(MAPPING_IOSB_TO_KIT_FILE));
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile(MAPPING_KIT_TO_IOSB_FILE));
        // call service B with A data

        long startTime = System.nanoTime();
        String serviceResultA = semanticRapClient.invokeServiceWithMapping(RESOURCE_ID_SERVICE_B, personAJson, true, Sets.newHashSet(PLATFORM_ID_A));
        long stopTime = System.nanoTime();
        LOG.info("###TIME###   total service with user A - " + (stopTime - startTime));
        LOG.info("\r\n");
//        assertEquals(personA, serviceResultA);
    }

    @Test
    public void testComplexMappingPerformance() throws Exception {
        int[] sizes = new int[]{1, 5, 10, 50, 100};
        for (int size : sizes) {
            for (int times = 0; times < 10; times++) {
                testComplexMappingN(size);
            }
        }
    }

    @Test
    public void testSimpleMappingPerformance() throws Exception {

        int[] sizes = new int[]{1, 5, 10, 50, 100};
        for (int size : sizes) {
            for (int times = 0; times < 10; times++) {
                testSimpleMappingN(size);
            }
        }
    }

    public void testSimpleMappingN(int dataSize) throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException, ParseException, IOException, UnsupportedMappingException {
        PersonA[] personA = new PersonA[dataSize];
        long result = 0;
        DataMapper mapper = new DataMapper();
        for (int i = 0; i < TEST_SIZE; i++) {
            for (int j = 0; j < dataSize; j++) {
                personA[j] = new PersonA(UUID.randomUUID().toString());
            }
            Model personARDF = JsonLDHelper.jsonLDToRdf(new JsonLDObjectMapper().writeValueAsString(personA));
            Mapping mapping = Mapping.parse(readResourceFile(MAPPING_A_TO_B_FILE).replace("&&&", System.lineSeparator()));

            long startTime = System.nanoTime();
            Model map = mapper.map(personARDF, mapping);
            long stopTime = System.nanoTime();
            result += (stopTime - startTime) / SCALING_FACTOR_ONE_RUN;
        }
        result /= (TEST_SIZE * SCALING_FACTOR_BATCH);
        LOG.info("###TIME###   simple mapping with object size " + dataSize + " - " + result);
        LOG.info("\r\n");
    }

    public void testComplexMappingN(int dataSize) throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException, ParseException, IOException, UnsupportedMappingException {
        String dataKIT = "[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://iosb.fraunhofer.de/ilt/ontologies/educampus#BleBeacon>, <http://www.w3.org/2002/07/owl#NamedIndividual> ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#beaconId> \"beacon1-iosb-beaconId\"^^<http://www.w3.org/2001/XMLSchema#string> ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#minor> \"1\"^^<http://www.w3.org/2001/XMLSchema#integer> ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#major> \"2\"^^<http://www.w3.org/2001/XMLSchema#integer> .\n"
                + "	\n"
                + "[] <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://iosb.fraunhofer.de/ilt/ontologies/educampus#Room>, <http://www.w3.org/2002/07/owl#NamedIndividual> ;\n"
                + "	<http://www.symbiote-h2020.eu/ontology/core#name> \"room1-iosb-name\" ;\n"
                + "	<http://www.symbiote-h2020.eu/ontology/core#description> \"room1-iosb-description\" ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#capacity> \"42\"^^<http://www.w3.org/2001/XMLSchema#integer> ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#roomNo> \"room1-iosb-roomNo\"^^<http://www.w3.org/2001/XMLSchema#string> ;\n"
                + "	<http://iosb.fraunhofer.de/ilt/ontologies/educampus#hasFeature> <http://iosb.fraunhofer.de/ilt/ontologies/educampus#airConditioning> , <http://iosb.fraunhofer.de/ilt/ontologies/educampus#whiteboard>, <http://iosb.fraunhofer.de/ilt/ontologies/educampus#projector> .";

        String testData = "";
        for (int j = 0; j < dataSize; j++) {
            testData += dataKIT + System.lineSeparator();
        }
        Model iosbRDF = ModelHelper.readModel(testData, RDFFormat.Turtle);
        Mapping mapping = Mapping.parse(readResourceFile(MAPPING_IOSB_TO_KIT_FILE).replace("&&&", System.lineSeparator()));
        long result = 0;
        DataMapper mapper = new DataMapper();
        for (int i = 0; i < TEST_SIZE; i++) {
            long startTime = System.nanoTime();
            Model map = mapper.map(iosbRDF, mapping);
            long stopTime = System.nanoTime();
            result += (stopTime - startTime) / SCALING_FACTOR_ONE_RUN;
        }
        result /= (TEST_SIZE * SCALING_FACTOR_BATCH);
        LOG.info("###TIME###   complex mapping with object size " + dataSize + " - " + result);
        LOG.info("\r\n");
    }
}
