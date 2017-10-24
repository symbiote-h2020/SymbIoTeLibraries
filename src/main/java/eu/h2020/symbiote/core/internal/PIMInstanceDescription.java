package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.model.mim.InterworkingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Class storing the Platform instance information. Contains platform label, comments, rdf info and
 * interworking service.
 *
 * Created by Szymon Mueller on 15/03/2017.
 *
 * @deprecated Integrated with {@link PIMMetaModelDescription}
 */
@Deprecated
public class PIMInstanceDescription extends RDFInfo {

    private String id;

    private List<String> labels = new ArrayList<>();

    private List<String> comments = new ArrayList<>();

    private List<InterworkingService> interworkingServices = new ArrayList<>();

    public PIMInstanceDescription() {
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

}
