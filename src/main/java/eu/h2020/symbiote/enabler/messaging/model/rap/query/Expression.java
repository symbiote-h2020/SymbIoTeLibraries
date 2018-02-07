/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.query;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class Expression extends Query {

    String param;
    Comparison.Cmp cmp;
    String val;
    
    @JsonCreator
    public Expression(String param, Comparison.Cmp cmp, String val) {
        this.param = param;
        this.cmp = cmp;
        this.val = val;
    }

    public String getParam() {
        return param;
    }

    public Comparison.Cmp getCmp() {
        return cmp;
    }

    public String getVal() {
        return val;
    }
}
