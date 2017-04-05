package eu.h2020.symbiote.cloud.model.data.parameter;


/**
 * Represents CIM-defined Range Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class RangeRestriction extends Restriction {

    private Double min;
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
