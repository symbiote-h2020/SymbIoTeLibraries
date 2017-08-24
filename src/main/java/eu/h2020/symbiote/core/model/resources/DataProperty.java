package eu.h2020.symbiote.core.model.resources;

/**
 * Class representing a Data Property defined in CIM.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PrimitiveProperty.class, name = "PrimitiveProperty"),
        @JsonSubTypes.Type(value = ComplexProperty.class, name = "ComplexProperty")
})
public class DataProperty {



}
