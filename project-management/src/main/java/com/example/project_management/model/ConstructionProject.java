package com.example.project_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "construction_projects")
@AllArgsConstructor
public class ConstructionProject implements Serializable {

    @Id
    private UUID id;

    private String name;
    private String location;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Material> materials;

    public ConstructionProject() {}

    @Override
    public String toString() {
        return "ConstructionProject(name=" + name + ", location=" + location + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstructionProject that = (ConstructionProject) o;
        return name.equals(that.name) &&
                location.equals(that.location) &&
                materials.equals(that.materials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, materials);
    }

}
