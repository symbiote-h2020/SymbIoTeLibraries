/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.query;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 */
public class Filter extends Query {
 
    Operator.Lop lop;
    List<Query> exprs = new ArrayList<>();
    
    @JsonCreator
    public Filter(@JsonProperty("lop")Operator.Lop lop, @JsonProperty("exprs")List<Query> exprs) {
         this.lop = lop;
         exprs.forEach((q) -> {
             this.exprs.add(q);
         });
    }

    public Operator.Lop getLop() {
        return lop;
    }

    public List<Query> getExprs() {
        return exprs;
    }
}
