package com.example.developer;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
public class Material implements Serializable {
    private String name;
    private String unit;
    private double unitPrice;
    private int quantity;

    @Override
    public String toString(){
        return "Material(name=" + name + ", unit=" + unit + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return name.equals(material.name) &&
                unit.equals(material.unit) &&
                unitPrice == material.unitPrice &&
                quantity == material.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, unit, unitPrice, quantity);
    }



}
