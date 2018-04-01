package eu.h2020.symbiote.model.cim;


import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class representing symbolic location defined in CIM.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
@QueryEntity
@Document
public class SymbolicLocation extends Location {

	public SymbolicLocation() {
		
	}
	
	
	public SymbolicLocation(SymbolicLocation l) {
		super(l.name, l.description);
	}


	@Override
	    public String toString() {
	    	return super.toString();
	    }
	    
	    
	    @Override
	    public boolean equals(Object other) {
	    	
	    	return super.equals(other);
	    }
	    
	    /**
	     * This class can be used as a hash index.
	     */
	    @Override
	    public int hashCode() {
	    	int result=super.hashCode();
	    	return result;
	    }
	
}
