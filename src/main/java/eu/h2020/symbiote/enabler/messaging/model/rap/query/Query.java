/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.query;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.query.Query} instead.  
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = Filter.class,       name = "filter"),
        @JsonSubTypes.Type(value = Expression.class,   name = "expr"),
})

public abstract class Query {
    
    public void execute() {}
}
