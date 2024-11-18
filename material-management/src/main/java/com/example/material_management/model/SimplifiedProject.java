package com.example.material_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name="simplified_projects")
public class SimplifiedProject {
    @Id
    private UUID id;

    private String name;

    public SimplifiedProject() {}

}
