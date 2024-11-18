package com.example.material_management.service;

import com.example.material_management.model.SimplifiedProject;
import com.example.material_management.repository.SimplifiedProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedProjectService {

    private final SimplifiedProjectRepository simplifiedProjectRepository;

    @Autowired
    public SimplifiedProjectService(SimplifiedProjectRepository repository) {
        this.simplifiedProjectRepository = repository;
    }

    public void createSimplifiedProject(UUID id, String name) {
        SimplifiedProject project = new SimplifiedProject(id, name);
        simplifiedProjectRepository.save(project);
    }

    public void deleteSimplifiedProject(UUID id) {
        simplifiedProjectRepository.deleteById(id);
    }

    public Optional<SimplifiedProject> findById(UUID id) {
        return simplifiedProjectRepository.findById(id);
    }

    public void updateSimplifiedProject(UUID id, String name) {
        SimplifiedProject project = simplifiedProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Simplified project not found with id " + id));
        project.setName(name);
        simplifiedProjectRepository.save(project);
    }
}

