/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.query;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.query.Comparison} instead.  
 */
public class Comparison {
    public enum Cmp {
        EQ, NE, LT, LE, GT, GE;
    }
    
    Cmp cmp;
    
    public Comparison(Cmp cmp) {
        this.cmp = cmp;
    }
    
    public Comparison(String cmpStr) throws Exception {
        switch(cmpStr) {
            case "EQ":
                this.cmp = Cmp.EQ;
                break;
            case "NE":
                this.cmp = Cmp.NE;
                break;
            case "LT":
                this.cmp = Cmp.LT;
                break;
            case "LE":
                this.cmp = Cmp.LE;
                break;
            case "GT":
                this.cmp = Cmp.GT;
                break;
            case "GE":
                this.cmp = Cmp.GE;
                break;
            default:
                throw new Exception("Comparison not recognize");
        }
    }
    
    public Cmp getCmp() {
        return cmp;
    }
    
    public boolean execute(String val1, String val2) throws Exception {    
        boolean result;
        switch(cmp) {
            case EQ:
                result = val1.equals(val2);
                break;
            case NE:
                result = !(val1.equals(val2));
                break;
            default:
                throw new Exception("Comparison to allowed between Strings");
        }
        
        return result;
    }
    
    public boolean execute(double val1, double val2) throws Exception {    
        boolean result;
        switch(cmp) {
            case EQ:
                result = (val1 == val2);
                break;
            case NE:
                result = (val1 != val2);
                break;
            case LT:
                result = (val1 < val2);
                break;
            case LE:
                result = (val1 <= val2);
                break;
            case GT:
                result = (val1 > val2);
                break;
            case GE:
                result = (val1 >= val2);
                break;
            default:
                throw new Exception("Not yet implemented");
        }
        
        return result;
    }
}
