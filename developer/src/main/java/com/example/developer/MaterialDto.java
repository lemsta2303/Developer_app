package com.example.developer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialDto  implements Comparable<MaterialDto> {
    private String name;
    private String unit;
    private double unitPrice;
    private int quantity;

    @Override
    public int compareTo(MaterialDto other) {
        return this.name.compareTo(other.name);
    }




}
