/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.querydsl.core.annotations.Config;
import com.querydsl.core.annotations.QueryEntity;
import com.querydsl.core.annotations.QueryInit;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 *
 * @author <a href="mailto:michael.jacoby@iosb.fraunhofer.de">Michael Jacoby</a>
 */
@QueryEntity
@Document
@Config(entityAccessors=true)
public class Sensor extends Device {
    private List<String> observesProperty;

    public List<String> getObservesProperty() {
        return observesProperty;
    }

    public void setObservesProperty(List<String> observesProperty) {
        this.observesProperty = observesProperty;
    }
}
