package eu.h2020.symbiote.core.internal.popularity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 8/3/2017.
 */
public class PopularityUpdatesMessage {

    @JsonProperty("popularityUpdateList")
    private List<PopularityUpdate> popularityUpdateList;

    public PopularityUpdatesMessage() {
        // empty constructor
    }

    public List<PopularityUpdate> getPopularityUpdateList() { return popularityUpdateList; }
    public void setPopularityUpdateList(List<PopularityUpdate> popularityUpdateList) {
        this.popularityUpdateList = popularityUpdateList;
    }

    public void addToPopularityUpdateList(PopularityUpdate popularityUpdate) {
        if (popularityUpdateList == null)
            popularityUpdateList = new ArrayList<>();

        popularityUpdateList.add(popularityUpdate);
    }
}
