package eu.h2020.symbiote.ssp.communication.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import eu.h2020.symbiote.ssp.exception.InvalidMacAddressException;

/**
 * Created by vasgl on 8/23/2017.
 */
public class DeviceDescriptor {

    @JsonProperty("mac")
    private String mac;

    @JsonProperty("sleeping")
    private Boolean sleeping;

    @JsonProperty("agentType")
    private AgentType agentType;

    @JsonProperty("readingInterval")
    private Integer readingInterval;

    public DeviceDescriptor() {
        // empty Constructor
    }

    public DeviceDescriptor(String mac, Boolean sleeping, AgentType agentType, Integer readingInterval)
            throws InvalidMacAddressException {

        setMac(mac);
        setSleeping(sleeping);
        setAgentType(agentType);
        setReadingInterval(readingInterval);
    }

    public String getMac() { return mac; }
    public void setMac(String mac) throws InvalidMacAddressException{
        // Check mac regex
        String macRegex = "^(((\\d|([a-f]|[A-F])){2}:){5}(\\d|([a-f]|[A-F])){2})$|^(((\\d|([a-f]|[A-F])){2}-){5}(\\d|([a-f]|[A-F])){2})$|^$";
        if (mac.matches(macRegex))
            this.mac = mac;
        else
            throw new InvalidMacAddressException(mac);
    }

    public Boolean getSleeping() { return sleeping; }
    public void setSleeping(Boolean sleeping) { this.sleeping = sleeping; }

    public AgentType getAgentType() { return agentType; }
    public void setAgentType(AgentType agentType) { this.agentType = agentType; }

    public Integer getReadingInterval() { return readingInterval; }
    public void setReadingInterval(Integer readingInterval) {
        if (readingInterval < 0)
            this.readingInterval = 0;
        else
            this.readingInterval = readingInterval; }
}
