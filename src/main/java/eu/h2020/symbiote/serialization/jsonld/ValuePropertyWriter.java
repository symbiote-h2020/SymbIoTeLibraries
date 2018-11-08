package eu.h2020.symbiote.serialization.jsonld;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import com.fasterxml.jackson.databind.util.Annotations;

/**
 * {@link BeanPropertyWriter} that holds a fixed value
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class ValuePropertyWriter extends VirtualBeanPropertyWriter {

    protected final Object value;

    protected ValuePropertyWriter(Object propertyValue, BeanPropertyDefinition propDef,
            Annotations contextAnnotations, JavaType declaredType) {
        this(propertyValue, propDef, contextAnnotations, declaredType, propDef.findInclusion());
    }

    protected ValuePropertyWriter(Object propertyValue, BeanPropertyDefinition propDef,
            Annotations contextAnnotations, JavaType declaredType,
            JsonInclude.Value inclusion) {
        super(propDef, contextAnnotations, declaredType, null, null, null, inclusion, null);
        this.value = propertyValue;
    }

    public static ValuePropertyWriter construct(Object propertyValue,
            BeanPropertyDefinition propDef,
            Annotations contextAnnotations,
            JavaType declaredType) {
        return new ValuePropertyWriter(propertyValue, propDef, contextAnnotations, declaredType);
    }

    @Override
    public VirtualBeanPropertyWriter withConfig(MapperConfig<?> mc, AnnotatedClass ac, BeanPropertyDefinition bpd, JavaType jt) {
        throw new IllegalStateException("Should not be called on this type");
    }

    @Override
    protected Object value(Object o, JsonGenerator jg, SerializerProvider sp) throws Exception {
        return value;
    }
}
