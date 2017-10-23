/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class ObservationValue {

    @JsonProperty("value")
    private final String value;
    @JsonProperty("obsProperty")
    private final Property obsProperty;
    @JsonProperty("uom")
    private final UnitOfMeasurement uom;

    @JsonCreator
    public ObservationValue(@JsonProperty("value") String value,
            @JsonProperty("obsProperty") Property obsProperty,
            @JsonProperty("uom") UnitOfMeasurement uom) {
        this.value = value;
        this.obsProperty = obsProperty;
        this.uom = uom;
    }

    public ObservationValue(ObservationValue other) {
        this.value = other.value;
        if (other.uom != null) {
            this.uom = new UnitOfMeasurement(other.uom);
        } else {
            this.uom = null;
        }
        if (other.obsProperty != null) {
            this.obsProperty = new Property(other.obsProperty);
        } else {
            this.obsProperty = null;
        }
    }

    public String getValue() {
        return value;
    }

    public Property getObsProperty() {
        return obsProperty;
    }

    public UnitOfMeasurement getUom() {
        return uom;
    }

    // Helper
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append("ObservationValue:");
        buffer.append("value=").append(value).append(",");
        buffer.append("uom=").append(uom).append(",");
        buffer.append("obsProp=").append(obsProperty);

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

        if (!(o instanceof ObservationValue)) {
            return false;
        }

        ObservationValue ov = (ObservationValue) o;

        if (!Objects.equals(this.value, ov.value)) {
            return false;
        }

        if (!Objects.equals(this.uom, ov.uom)) {
            return false;
        }

        if (!Objects.equals(this.obsProperty, ov.obsProperty)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 42;
        result += 3 * Objects.hashCode(this.value);
        result += 5 * Objects.hashCode(this.uom);
        result += 5 * Objects.hashCode(this.obsProperty);

        return result;
    }

}
