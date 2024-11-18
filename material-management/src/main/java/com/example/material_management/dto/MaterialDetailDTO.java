package com.example.material_management.dto;

import com.example.material_management.model.Material;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MaterialDetailDTO {
    private UUID id;
    private String name;
    private String unit;
    private double unitPrice;
    private int quantity;
    private UUID projectId;

    public static MaterialDetailDTO from(Material material) {
        return MaterialDetailDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .unit(material.getUnit())
                .unitPrice(material.getUnitPrice())
                .quantity(material.getQuantity())
                .projectId(material.getProject().getId())
                .build();
    }
}
