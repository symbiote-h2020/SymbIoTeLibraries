/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.core.model.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Matteo Pardi <m.pardi@nextworks.it>
 */
public class ParameterValue {
    @JsonProperty("value")
    private final String value;
    @JsonProperty("uom")
    private final UnitOfMeasurement uom;

    @JsonCreator
    public ParameterValue(@JsonProperty("value") String value,
                            @JsonProperty("uom") UnitOfMeasurement uom) {
        this.value = value;
        this.uom = uom;
    }

    public String getValue() {
        return value;
    }

    public UnitOfMeasurement getUom() {
        return uom;
    }
}
