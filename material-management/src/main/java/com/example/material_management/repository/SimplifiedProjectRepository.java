package com.example.material_management.repository;

import com.example.material_management.model.SimplifiedProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SimplifiedProjectRepository extends JpaRepository<SimplifiedProject, UUID> {
}

