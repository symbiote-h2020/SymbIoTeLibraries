/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.query;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class Operator {
    public enum Lop {
        AND, OR, NOT
    }
    
    Lop lop;
    
    public Operator(Lop lop) {
        this.lop = lop;
    }
    
    public Operator(String lopStr) throws Exception {
        switch(lopStr){
            case "AND":
                this.lop = lop.AND;
                break;
            case "OR":
                this.lop = lop.OR;
                break;
            case "NOT":
                this.lop = lop.NOT;
                break;
            default:
                throw new Exception("Operator not recognize");
        }
        
    }
    
    public Lop getLop() {
        return lop;
    }
    
    public boolean execute(boolean... expr) throws Exception {    
        boolean result;
        if(lop == Lop.NOT) {
            if(expr.length != 1) {
                throw new Exception("Operator allows only one expression");
            }
            result = !expr[0];
        } else {
            result = (lop == Lop.AND);
            for (int i=0; i<expr.length; ++i) {
                if((lop == Lop.AND) && (!expr[i])) {
                    result=false;
                    break;
                } else if ((lop == Lop.OR) && expr[i]) {
                    result=true;
                    break;
                }
            }
        }
        
        return result;
    }
}
