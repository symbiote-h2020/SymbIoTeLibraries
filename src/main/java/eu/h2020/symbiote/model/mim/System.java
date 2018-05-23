package eu.h2020.symbiote.model.mim;

import eu.h2020.symbiote.core.internal.RDFInfo;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Represents a System MIM entity.
 *
 * Created by Szymon Mueller on 23/05/2018.
 */
public class System extends RDFInfo {

    @Id
    private String id;

    private String name;

    private List<String> description;

    private List<InterworkingService> interworkingServices;

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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<InterworkingService> getInterworkingServices() {
        return interworkingServices;
    }

    public void setInterworkingServices(List<InterworkingService> interworkingServices) {
        this.interworkingServices = interworkingServices;
    }


}
