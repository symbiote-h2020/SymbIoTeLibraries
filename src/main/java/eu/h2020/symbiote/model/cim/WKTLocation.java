package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class representing WKT location defined in CIM.
 *
 * Created by Szymon Mueller on 01/05/2017.
 */
public class WKTLocation extends Location {

    @JsonProperty("value")
    private String value;

    public WKTLocation() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
