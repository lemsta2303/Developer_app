package com.example.project_management.repository;

import com.example.project_management.model.ConstructionProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ConstructionProjectRepository extends JpaRepository<ConstructionProject, UUID> {

}
