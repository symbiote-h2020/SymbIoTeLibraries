package eu.h2020.symbiote.model.mim;

import eu.h2020.symbiote.core.internal.RDFInfo;

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

    /**
     * Name of the information model
     */
    private String name;

    /**
     * Owner (creator) of the information model.
     */
    private String owner;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
