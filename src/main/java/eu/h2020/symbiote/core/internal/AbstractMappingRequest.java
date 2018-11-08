package eu.h2020.symbiote.core.internal;

public abstract class AbstractMappingRequest {

    private boolean getDefinition = false;

    public boolean isGetDefinition() { return getDefinition; }
    public void setGetDefinition(boolean getDefinition) { this.getDefinition = getDefinition; }
}
