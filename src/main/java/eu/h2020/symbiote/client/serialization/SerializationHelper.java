/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class SerializationHelper {

    private static final String EMPTY_SERVICE_PARAMETER = "[{}]";

    private SerializationHelper() {
    }

    public static String serializeServiceParameter(Object parameter) {
        if (parameter == null) {
            return EMPTY_SERVICE_PARAMETER;
        }
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(parameter.getClass(), new ServiceParameterSerializer<>());
        mapper.registerModule(simpleModule);
        try {
            return mapper.writeValueAsString(parameter);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("service parameter could not be serialized", ex);
        }
    }
}
