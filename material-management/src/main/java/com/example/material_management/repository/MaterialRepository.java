package com.example.material_management.repository;

import com.example.material_management.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
    List<Material> findByProject(UUID projectId);

    void deleteByProjectId(UUID projectId);
}
