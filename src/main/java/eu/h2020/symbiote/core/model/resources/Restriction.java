package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents CIM-defined Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EnumRestriction.class, name = "EnumRestriction"),
        @JsonSubTypes.Type(value = LengthRestriction.class, name = "LengthRestriction"),
        @JsonSubTypes.Type(value = RangeRestriction.class, name = "RangeRestriction")
})
public abstract class Restriction {

}
