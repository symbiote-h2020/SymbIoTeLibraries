/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.resources;

import eu.h2020.symbiote.core.model.Observation;
import eu.h2020.symbiote.core.model.resources.Resource;
import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Matteo Pardi <m.pardi@nextworks.it>
 */
public class Sensor extends Resource {
    @Id
    private String id;
    private List<Observation> observations;
    
    public Sensor() {
        
    }
    
    public List<Observation> getObservations() {
        return observations;
    }
    
}