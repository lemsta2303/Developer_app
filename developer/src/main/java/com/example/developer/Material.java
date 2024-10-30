package com.example.developer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "materials")
@AllArgsConstructor
public class Material implements Serializable {

    @Id
    private UUID id;

    private String name;
    private String unit;

    @Column(name = "unit_price")
    private double unitPrice;
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private ConstructionProject project;

    public Material() {

    }

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
