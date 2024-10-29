package com.example.developer;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class ConstructionProject implements Serializable {
    private String name;
    private String location;
    private List<Material> materials;

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
