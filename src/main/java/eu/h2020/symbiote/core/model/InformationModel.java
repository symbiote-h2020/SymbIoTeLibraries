package eu.h2020.symbiote.core.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Representation of the Information Model from the CIM.
 *
 * Created by Szymon Mueller on 15/03/2017.
 */
public class InformationModel extends RDFInfo {

    /**
     * Symbiote Id of information model
     */
    private String id;

    /**
     * Unique URI of the information model
     */
    private String uri;

    public InformationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
