package com.example.developer.service;

import com.example.developer.dto.ConstructionProjectCreateUpdateDTO;
import com.example.developer.dto.ConstructionProjectDetailDTO;
import com.example.developer.dto.ConstructionProjectSummaryDTO;
import com.example.developer.model.ConstructionProject;
import com.example.developer.repository.ConstructionProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConstructionProjectService {

    private final ConstructionProjectRepository constructionProjectRepository;

    @Autowired
    public ConstructionProjectService(ConstructionProjectRepository constructionProjectRepository) {
        this.constructionProjectRepository = constructionProjectRepository;
    }


    // methods for consolecommandrunner
    public List<ConstructionProject> findAll() {
        return constructionProjectRepository.findAll();
    }

    public Optional<ConstructionProject> findById(UUID id) {
        return constructionProjectRepository.findById(id);
    }

    public void save(ConstructionProject project) {
        constructionProjectRepository.save(project);
    }

    public void deleteById(UUID id) {
        constructionProjectRepository.deleteById(id);
    }


    // methods for controller
    public void createProject(ConstructionProjectCreateUpdateDTO projectDTO) {
        ConstructionProject project = ConstructionProject.builder()
                .id(UUID.randomUUID())
                .name(projectDTO.getName())
                .location(projectDTO.getLocation())
                .build();
        constructionProjectRepository.save(project);
    }

    public ConstructionProjectDetailDTO getProjectById(UUID id) {
        ConstructionProject project = constructionProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        return ConstructionProjectDetailDTO.from(project);
    }

    public List<ConstructionProjectSummaryDTO> getAllProjects() {
        List<ConstructionProject> projects = constructionProjectRepository.findAll();
        return projects.stream()
                .map(ConstructionProjectSummaryDTO::from)
                .collect(Collectors.toList());
    }

    public void updateProject(UUID id, ConstructionProjectCreateUpdateDTO projectDTO) {
        ConstructionProject project = constructionProjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id));

        project.setName(projectDTO.getName());
        project.setLocation(projectDTO.getLocation());

        constructionProjectRepository.save(project);
    }

    public void deleteProject(UUID id) {
        constructionProjectRepository.deleteById(id);
    }
}
