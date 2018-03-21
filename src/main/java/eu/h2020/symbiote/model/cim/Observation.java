/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author <a href="mailto:aleksandar.antonic@fer.hr">Aleksandar Antonic</a>
 */
public class Observation {

    @JsonProperty("resourceId")
    private final String resourceId;
    @JsonProperty("location")
    private final Location location;
    @JsonProperty("resultTime")
    private final String resultTime;
    @JsonProperty("samplingTime")
    private final String samplingTime;
    @JsonProperty("obsValues")
    private final List<ObservationValue> obsValues;

    @JsonCreator
    public Observation(@JsonProperty("resourceId") String resourceId,
                       @JsonProperty("location") Location location,
                       @JsonProperty("resultTime") String resultTime,
                       @JsonProperty("samplingTime") String samplingTime,
                       @JsonProperty("obsValues") List<ObservationValue> obsValues) {
        this.resourceId = resourceId;
        this.location = location;
        this.resultTime = resultTime;
        this.samplingTime = samplingTime;
        this.obsValues = obsValues;
    }

    public Observation(Observation other) {
        this.resourceId = other.resourceId;
        this.location = Location.makeCopy(other.location);
        this.resultTime = other.resultTime;
        this.samplingTime = other.samplingTime;
        if (other.obsValues == null) {
            this.obsValues = null;
        } else {
            this.obsValues = new ArrayList<ObservationValue>();

            for (ObservationValue obsValue : other.obsValues) {
                ObservationValue newOV = obsValue == null ? null : new ObservationValue(obsValue);
                this.obsValues.add(newOV);
            }
        }

    }

    public String getResourceId() {
        return resourceId;
    }

    public Location getLocation() {
        return location;
    }

    public String getResultTime() {
        return resultTime;
    }

    public String getSamplingTime() {
        return samplingTime;
    }

    public List<ObservationValue> getObsValues() {
        return obsValues;
    }

    // Helper
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("Observation by ").append(this.resourceId).append("@").append(this.location).append("\n");
        buffer.append("Made at ").append(samplingTime).append("for ").append(resultTime).append("\n");

        buffer.append("ObservationValues:");
        if (this.obsValues == null) {
            buffer.append("null");
        } else {
            int l = this.obsValues.size();
            if (l > 3) {
                l = 3;
            }
            buffer.append(this.obsValues.subList(0, l)).append("\n");
            if (this.obsValues.size() > 3) {
                buffer.append("... (").append(this.obsValues.size() - 3).append("more values\n");
            }
        }

        return buffer.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Observation)) {
            return false;
        }

        Observation obs = (Observation) o;

        if (!Objects.equals(this.resourceId, obs.resourceId)) {
            return false;
        }

        if (!Objects.equals(this.resultTime, obs.resultTime)) {
            return false;
        }

        if (!Objects.equals(this.samplingTime, obs.samplingTime)) {
            return false;
        }

        if (!Objects.equals(this.location, obs.location)) {
            return false;
        }

        if (!Objects.equals(this.obsValues, obs.obsValues)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 42;

        result += Objects.hashCode(this.resourceId) * 3;
        result += Objects.hashCode(this.resultTime) * 5;
        result += Objects.hashCode(this.samplingTime) * 7;
        result += Objects.hashCode(this.location) * 11;
        result += Objects.hashCode(this.obsValues) * 13;

        return result;
    }
}
