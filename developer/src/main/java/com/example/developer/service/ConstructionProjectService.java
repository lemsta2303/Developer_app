package com.example.developer.service;

import com.example.developer.model.ConstructionProject;
import com.example.developer.repository.ConstructionProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConstructionProjectService {

    private final ConstructionProjectRepository constructionProjectRepository;

    @Autowired
    public ConstructionProjectService(ConstructionProjectRepository constructionProjectRepository) {
        this.constructionProjectRepository = constructionProjectRepository;
    }


    public List<ConstructionProject> findAll() {
        return constructionProjectRepository.findAll();
    }

    public Optional<ConstructionProject> findById(UUID id) {
        return constructionProjectRepository.findById(id);
    }

    public ConstructionProject save(ConstructionProject project) {
        return constructionProjectRepository.save(project);
    }

    public void deleteById(UUID id) {
        constructionProjectRepository.deleteById(id);
    }
}
