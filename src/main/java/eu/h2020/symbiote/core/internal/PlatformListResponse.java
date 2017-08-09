package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.Platform;

import java.util.List;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * Created by mateuszl on 09.08.2017.
 */
public class PlatformListResponse extends StatusResponse<List<Platform>> {

    public PlatformListResponse() {
    }

    public PlatformListResponse(int status, String message, List<Platform> body) {
        super(status, message, body);
    }

    public void setPlatforms(List<Platform> platforms){
        super.setBody(platforms);
    }

    public List<Platform> getPlatforms(){
        return super.getBody();
    }
}
