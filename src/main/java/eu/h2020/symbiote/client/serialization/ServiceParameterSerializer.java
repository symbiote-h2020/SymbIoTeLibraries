/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class ServiceParameterSerializer<T> extends StdSerializer<T> {

    public ServiceParameterSerializer() {
        this(null);
    }

    public ServiceParameterSerializer(Class<T> t) {
        super(t);
    }

    @Override
    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        BeanDescription introspect = serializerProvider.getConfig().introspect(serializerProvider.getConfig().constructType(t.getClass()));
        for (BeanPropertyDefinition property : introspect.findProperties()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName(property.getName());
            serializerProvider.defaultSerializeValue(property.getAccessor().getValue(t), jsonGenerator);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }

}
