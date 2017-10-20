package eu.h2020.symbiote.model.mim;

import eu.h2020.symbiote.core.internal.RDFInfo;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Class representing platforms (and enablers). Instances of the platforms are defined in Administration and are
 * stored in the Registry.
 */
public class Platform extends RDFInfo {

    @Id
    private String id;
    private List<String> labels;
    private List<String> comments;
    private List<InterworkingService> interworkingServices;
    private boolean enabler = false;

    public Platform() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<InterworkingService> getInterworkingServices() {
        return interworkingServices;
    }

    public void setInterworkingServices(List<InterworkingService> interworkingServices) {
        this.interworkingServices = interworkingServices;
    }

    public boolean isEnabler() {
        return enabler;
    }

    public void setEnabler(boolean enabler) {
        this.enabler = enabler;
    }
}