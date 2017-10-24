package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined Length Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class LengthRestriction extends Restriction {

    @JsonProperty("min")
    private Integer min;
    @JsonProperty("max")
    private Integer max;

    public LengthRestriction() {
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
