/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.query;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 */
public class Filter extends Query {
 
    Operator.Lop lop;
    ArrayList<Query> exprs = new ArrayList();
    
    @JsonCreator
    public Filter(@JsonProperty("lop")Operator.Lop lop, @JsonProperty("exprs")ArrayList<Query> exprs) {
         this.lop = lop;
         exprs.forEach((q) -> {
             this.exprs.add(q);
         });
    }

    public Operator.Lop getLop() {
        return lop;
    }

    public ArrayList<Query> getExprs() {
        return exprs;
    }
}
