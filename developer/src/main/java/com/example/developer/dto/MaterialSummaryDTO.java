package com.example.developer.dto;

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
}

