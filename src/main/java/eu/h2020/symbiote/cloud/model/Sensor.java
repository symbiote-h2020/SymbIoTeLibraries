/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.core.model.Observation;
import java.util.List;

/**
 *
 * @author Matteo Pardi <m.pardi@nextworks.it>
 */
public class Sensor extends Resource {
    List<Observation> observations;
    
    public Sensor() {
        
    }
}
