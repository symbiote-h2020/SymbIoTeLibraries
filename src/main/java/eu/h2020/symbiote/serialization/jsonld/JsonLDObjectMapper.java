package eu.h2020.symbiote.serialization.jsonld;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/**
 * subclass of Jackson {@link ObjectMapper} that understands {@link JsonLDType}
 * annotation
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class JsonLDObjectMapper extends ObjectMapper {

    private final ObjectMapper defaultMapper;

    public JsonLDObjectMapper() {
        super();
        this.defaultMapper = new ObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, true);
        setAnnotationIntrospector(AnnotationIntrospector.pair(new JsonLDTypeAnnotationIntrospector(), getSerializationConfig().getAnnotationIntrospector()));
    }

    public JsonLDObjectMapper(boolean includeTypesFromSuperclasses, boolean alwaysUseArray) {
        super();
        this.defaultMapper = new ObjectMapper().configure(MapperFeature.USE_ANNOTATIONS, true);
        setAnnotationIntrospector(AnnotationIntrospector.pair(new JsonLDTypeAnnotationIntrospector(includeTypesFromSuperclasses, alwaysUseArray), getSerializationConfig().getAnnotationIntrospector()));
    }

    private JsonParser preprocess(JsonParser parser) throws IOException {
        return defaultMapper.treeAsTokens(removeTypeInfo(defaultMapper.readTree(parser)));
    }

    private static JsonNode removeTypeInfo(JsonNode node) {
        if (node.isObject()) {
            ObjectNode rootObject = (ObjectNode) node;
            rootObject.remove("@type");
        }
        node.elements().forEachRemaining(x -> removeTypeInfo(x));
        return node;
    }

    @Override
    protected Object _readValue(DeserializationConfig cfg, JsonParser parser, JavaType valueType) throws IOException {
        return super._readValue(cfg, preprocess(parser), valueType);
    }

    @Override
    public <T> MappingIterator<T> readValues(JsonParser parser, JavaType valueType)
            throws IOException, JsonProcessingException {
        return super.readValues(preprocess(parser), valueType);
    }

    @Override
    protected JsonNode _readTreeAndClose(JsonParser parser) throws IOException {
        return super._readTreeAndClose(preprocess(parser));
    }

    @Override
    protected Object _readMapAndClose(JsonParser parser, JavaType valueType) throws IOException {
        return super._readMapAndClose(preprocess(parser), valueType);
    }

}
