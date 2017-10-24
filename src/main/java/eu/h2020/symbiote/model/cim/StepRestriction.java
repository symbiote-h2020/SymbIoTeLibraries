package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined Step Restriction class.
 *
 * Created by Mael on 28/03/2017.
 */
public class StepRestriction extends RangeRestriction {

    @JsonProperty("step")
    private Double step;

    public StepRestriction() {
    }

    public Double getStep() {
        return step;
    }

    public void setStep(Double step) {
        this.step = step;
    }
}
