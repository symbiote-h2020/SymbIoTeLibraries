package eu.h2020.symbiote.model.cim;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing WKT location defined in CIM.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
public class WKTLocation extends Location {

    @JsonProperty("value")
    private String value;

    public WKTLocation() {
    }

    public WKTLocation(WKTLocation l) {
    	super(l.labels, l.comments);
    	this.value=l.value;
	}

	public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
    @Override
    public String toString() {
    	StringBuffer b=new StringBuffer();
    	b.append("A WKT location,  value=").append(this.value);
    	b.append("\n");
    	b.append(super.toString());
    	return b.toString();
    }
    
    
    @Override
    public boolean equals(Object other) {
    	
    	if ( ! super.equals(other) )	// This call also includes the obligatory checks for identity, null and so on.
    		return false;
    	
    	if ( ! (other instanceof WKTLocation) )
    		return false;
    	
    	WKTLocation o=(WKTLocation)other;
    	
    	if (! Objects.equals(this.value, o.value))
    		return false;
    	
    	return true;
    }
    
    /**
     * This class can be used as a hash index.
     */
    @Override
    public int hashCode() {
    	int result=super.hashCode();
    	if (this.value!=null)
    		result+=value.hashCode()*17;
    	return result;
    }
}
