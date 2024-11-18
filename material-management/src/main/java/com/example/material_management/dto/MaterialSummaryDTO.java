package com.example.material_management.dto;

import com.example.material_management.model.Material;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class MaterialSummaryDTO {
    private UUID id;
    private String name;

    public static MaterialSummaryDTO from(Material material) {
        return MaterialSummaryDTO.builder()
                .id(material.getId())
                .name(material.getName())
                .build();
    }
}

