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
    private List<String> observesProperty;

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
