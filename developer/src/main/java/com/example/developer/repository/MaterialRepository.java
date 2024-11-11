package com.example.developer.repository;

import com.example.developer.model.ConstructionProject;
import com.example.developer.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    List<Material> findByProject(ConstructionProject project);
}
