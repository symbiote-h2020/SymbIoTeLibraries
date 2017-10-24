package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined RegEx Restriction class.
 *
 * Created by Szymon Mueller 26/07/2017.
 */
public class RegExRestriction extends Restriction {

    @JsonProperty("pattern")
    private String pattern;

    public RegExRestriction() {
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
