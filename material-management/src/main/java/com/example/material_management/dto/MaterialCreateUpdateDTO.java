package com.example.material_management.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MaterialCreateUpdateDTO {
    private String name;
    private String unit;
    private double unitPrice;
    private int quantity;
}
