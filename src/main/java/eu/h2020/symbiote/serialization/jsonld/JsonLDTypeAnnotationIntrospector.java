package eu.h2020.symbiote.serialization.jsonld;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VirtualAnnotatedMember;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * subclass of {@link JacksonAnnotationIntrospector} that uses
 * {@link JsonLDType} annotation to produce JSON-LD upon serialization
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class JsonLDTypeAnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final String PROPERTY_NAME = "@type";
    private boolean includeTypesFromSuperclasses = true;
    private boolean alwaysUseArray = false;

    public JsonLDTypeAnnotationIntrospector() {
    }

    public JsonLDTypeAnnotationIntrospector(boolean includeTypesFromSuperclasses, boolean alwaysUseArray) {
        this.includeTypesFromSuperclasses = includeTypesFromSuperclasses;
        this.alwaysUseArray = alwaysUseArray;
    }

    private Set<String> findJsonLDTypeAnnotation(JavaType type) {
        Stream<Annotation> annotations = isIncludeTypesFromSuperclasses()
                ? ClassUtil.findSuperTypes(type, null, true).stream()
                        .flatMap(x -> Stream.of(ClassUtil.findClassAnnotations(x.getRawClass())))
                : Stream.of(ClassUtil.findClassAnnotations(type.getRawClass()));
        return annotations
                .filter(x -> Objects.equals(x.annotationType(), JsonLDType.class))
                .flatMap(x -> Stream.of(((JsonLDType) x).value()))
                .collect(Collectors.toSet());
    }

    @Override
    public void findAndAddVirtualProperties(MapperConfig<?> config, AnnotatedClass ac, List<BeanPropertyWriter> properties) {
        Set<String> types = findJsonLDTypeAnnotation(ac.getType());
        if (types.isEmpty()) {
            return;
        }
        PropertyName propertyName = _propertyName(PROPERTY_NAME, "");
        JavaType javaType = (types.size() == 1 && !isAlwaysUseArray())
                ? config.constructType(String.class)
                : config.constructType(String[].class);
        Object value = (types.size() == 1 && !isAlwaysUseArray())
                ? types.iterator().next()
                : types.toArray(new String[types.size()]);

        AnnotatedMember member = new VirtualAnnotatedMember(ac, ac.getRawType(), PROPERTY_NAME, javaType);

        SimpleBeanPropertyDefinition propDef = SimpleBeanPropertyDefinition.construct(config,
                member, propertyName, PropertyMetadata.STD_OPTIONAL, Include.USE_DEFAULTS);
        ValuePropertyWriter writer = ValuePropertyWriter.construct(value, propDef,
                ac.getAnnotations(), javaType);
        properties.add(writer);
    }

    public boolean isIncludeTypesFromSuperclasses() {
        return includeTypesFromSuperclasses;
    }

    public void setIncludeTypesFromSuperclasses(boolean includeTypesFromSuperclasses) {
        this.includeTypesFromSuperclasses = includeTypesFromSuperclasses;
    }

    public boolean isAlwaysUseArray() {
        return alwaysUseArray;
    }

    public void setAlwaysUseArray(boolean alwaysUseArray) {
        this.alwaysUseArray = alwaysUseArray;
    }

}
