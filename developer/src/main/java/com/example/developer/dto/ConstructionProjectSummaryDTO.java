package com.example.developer.dto;

import com.example.developer.model.ConstructionProject;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ConstructionProjectSummaryDTO {
    private UUID id;
    private String name;

    public static ConstructionProjectSummaryDTO from(ConstructionProject project) {
        return ConstructionProjectSummaryDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }

}

