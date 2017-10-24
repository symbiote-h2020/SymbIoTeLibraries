package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.model.mim.InformationModel;

/**
 * Class storing the Meta Information Model information about the model.
 *
 * Created by Szymon Mueller on 15/03/2017.
 *
 * @deprecated changed for {@link InformationModel}
 */
@Deprecated
public class PIMMetaModelDescription extends RDFInfo {

    private String uri;

    private String id;

    private String name;

    private String definition;

    public PIMMetaModelDescription() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
