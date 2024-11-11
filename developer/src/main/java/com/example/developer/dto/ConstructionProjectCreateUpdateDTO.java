package com.example.developer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConstructionProjectCreateUpdateDTO {
    private String name;
    private String location;
}

