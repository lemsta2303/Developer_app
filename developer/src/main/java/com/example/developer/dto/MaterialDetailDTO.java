package com.example.developer.dto;

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
}
