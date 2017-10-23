/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import java.util.List;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class Sensor extends Device {
    private List<Observation> observesProperty;

    /**
     * @return the observesProperty
     */
    public List<Observation> getObservesProperty() {
        return observesProperty;
    }

    /**
     * @param observesProperty the observesProperty to set
     */
    public void setObservesProperty(List<Observation> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
