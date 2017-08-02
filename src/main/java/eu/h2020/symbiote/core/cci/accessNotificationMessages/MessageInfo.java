package eu.h2020.symbiote.core.cci.accessNotificationMessages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Luca Tomaselli <l.tomaselli@nextworks.it>
 */
public class MessageInfo {
    @JsonProperty("symbIoTeId")
    protected String symbIoTeId;
    @JsonProperty("timestamp")
    protected List<Date> timestamps;

    public MessageInfo() {
        // empty constructor
    }

    public String getSymbIoTeId() {
        return symbIoTeId;
    }
    public void setSymbIoTeId(String symbIoTeId) {
        this.symbIoTeId = symbIoTeId;
    }

    public List<Date> getTimestamps() {
        return timestamps;
    }
    public void setTimestamps(List<Date> timestamps) {
        this.timestamps = timestamps;
    }
}
