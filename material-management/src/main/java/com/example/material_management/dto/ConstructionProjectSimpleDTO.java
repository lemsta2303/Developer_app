package com.example.material_management.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ConstructionProjectSimpleDTO {

    //simplified version of the ConstructionProject entity
    @Id
    private UUID id;

    private String name;

}

