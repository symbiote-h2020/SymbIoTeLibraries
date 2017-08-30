package eu.h2020.symbiote.ssp.communication.rabbit;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/30/2017.
 */
public class SSPRecourceCreated {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    public SSPRecourceCreated() {
    }

    public SSPRecourceCreated(String id, String url) {
        setId(id);
        setUrl(url);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
