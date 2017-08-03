package eu.h2020.symbiote.core.internal.popularity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vasgl on 8/3/2017.
 */
public class PopularityUpdate {

    @JsonProperty("id")
    private String id;

    @JsonProperty("viewsInDefinedInterval")
    private Integer viewsInDefinedInterval;

    public PopularityUpdate() {
        // empty constructor
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Integer getViewsInDefinedInterval() { return viewsInDefinedInterval; }
    public void setViewsInDefinedInterval(Integer views) { this.viewsInDefinedInterval = views; }

}
