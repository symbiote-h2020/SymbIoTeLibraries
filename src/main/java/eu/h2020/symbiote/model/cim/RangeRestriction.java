package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined Range Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class RangeRestriction extends Restriction {

    @JsonProperty("min")
    private Double min;
    @JsonProperty("max")
    private Double max;

    public RangeRestriction() {
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }
}
