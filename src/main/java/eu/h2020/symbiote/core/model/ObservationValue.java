/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class ObservationValue {
    @JsonProperty("value")
    private final Object value;
    @JsonProperty("obsProperty")
    private final Property obsProperty;
    @JsonProperty("uom")
    private final UnitOfMeasurement uom;

    @JsonCreator
    public ObservationValue(@JsonProperty("value") Object value, 
                            @JsonProperty("obsProperty") Property obsProperty, 
                            @JsonProperty("uom") UnitOfMeasurement uom) {
        this.value = value;
        this.obsProperty = obsProperty;
        this.uom = uom;
    }

    public Object getValue() {
        return value;
    }

    public Property getObsProperty() {
        return obsProperty;
    }

    public UnitOfMeasurement getUom() {
        return uom;
    }

}
