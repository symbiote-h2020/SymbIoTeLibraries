package eu.h2020.symbiote.serialization.jsonld;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Attribute to define JSON-LD @type property. If one one type is specified than
 * result will be <code>"@type": "value"</code>, if multiple types are specified
 * result will be <code>"@type": ["value1", ..., "valueN"]</code>
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
@Target(value = {ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonLDType {

    public String[] value() default "";
}
