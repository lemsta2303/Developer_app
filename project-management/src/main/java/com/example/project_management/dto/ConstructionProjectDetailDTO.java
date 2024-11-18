package com.example.project_management.dto;

import com.example.project_management.model.ConstructionProject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ConstructionProjectDetailDTO {
    private UUID id;
    private String name;
    private String location;
//    private List<MaterialSummaryDTO> materials;

    public static ConstructionProjectDetailDTO from(ConstructionProject project) {
        return ConstructionProjectDetailDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .location(project.getLocation())
//                .materials(project.getMaterials().stream()
//                        .map(material -> MaterialSummaryDTO.builder()
//                                .id(material.getId())
//                                .name(material.getName())
//                                .build())
//                        .collect(Collectors.toList()))
                .build();
    }
}

