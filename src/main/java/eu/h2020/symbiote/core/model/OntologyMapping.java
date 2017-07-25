package eu.h2020.symbiote.core.model;

/**
 * Represents a single mapping between two information models.
 *
 * Created by Szymon Mueller on 25/07/2017.
 */
public class OntologyMapping {

    private String id;

    private String name;

    private String sourceModelId;

    private String destinationModelId;

    private String definition;

    private OntologyMappingFormat definitionFormat;

    public OntologyMapping() {
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

    public String getSourceModelId() {
        return sourceModelId;
    }

    public void setSourceModelId(String sourceModelId) {
        this.sourceModelId = sourceModelId;
    }

    public String getDestinationModelId() {
        return destinationModelId;
    }

    public void setDestinationModelId(String destinationModelId) {
        this.destinationModelId = destinationModelId;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public OntologyMappingFormat getDefinitionFormat() {
        return definitionFormat;
    }

    public void setDefinitionFormat(OntologyMappingFormat definitionFormat) {
        this.definitionFormat = definitionFormat;
    }
}
