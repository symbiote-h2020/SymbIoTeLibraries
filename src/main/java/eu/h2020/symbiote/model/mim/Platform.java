package eu.h2020.symbiote.model.mim;

import eu.h2020.symbiote.core.internal.RDFInfo;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Class representing platforms (and enablers). Instances of the platforms are defined in Administration and are
 * stored in the Registry.
 */
public class Platform extends System {

    private boolean enabler = false;

    public Platform() {

    }

    public boolean isEnabler() {
        return enabler;
    }

    public void setEnabler(boolean enabler) {
        this.enabler = enabler;
    }
}