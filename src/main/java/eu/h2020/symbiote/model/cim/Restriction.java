package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents CIM-defined Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EnumRestriction.class, name = "EnumRestriction"),
        @JsonSubTypes.Type(value = LengthRestriction.class, name = "LengthRestriction"),
        @JsonSubTypes.Type(value = RangeRestriction.class, name = "RangeRestriction"),
        @JsonSubTypes.Type(value = RegExRestriction.class, name = "RegExRestriction"),
        @JsonSubTypes.Type(value = InstanceOfRestriction.class, name = "InstanceOfRestriction")
})
public abstract class Restriction {

}
