package eu.h2020.symbiote.core.model.resources;

/**
 * Represents CIM-defined Length Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class LengthRestriction extends Restriction {

    private Integer min;
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
