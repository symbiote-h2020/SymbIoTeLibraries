/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * @author Aleksandar Antonic <aleksandar.antonic@fer.hr>
 */
public class UnitOfMeasurement {

    @JsonProperty("symbol")
    private final String symbol;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("description")
    private final List<String> description;

    @JsonCreator
    public UnitOfMeasurement(@JsonProperty("symbol") String symbol,
            @JsonProperty("name") String name,
            @JsonProperty("description") List<String> description) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
    }

    public UnitOfMeasurement(UnitOfMeasurement other) {
        this.symbol = other.symbol;
        this.name = other.name;
        this.description = other.description;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public List<String> getDescription() {
        return description;
    }

    // Helper
    @Override
    public String toString() {
        return symbol == null ? "null" : symbol.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof UnitOfMeasurement)) {
            return false;
        }

        UnitOfMeasurement ou = (UnitOfMeasurement) o;

        if (this.symbol == null && ou.symbol == null) {
            return true;
        }

        if (this.symbol == null) // We already know, the other is not null
        {
            return false;
        }

        return this.symbol.equals(ou.symbol);
    }

    @Override
    public int hashCode() {
        if (this.symbol == null) {
            return 0;
        }

        return this.symbol.hashCode();
    }

}
