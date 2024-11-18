package com.example.project_management.service;

import com.example.project_management.dto.ConstructionProjectCreateUpdateDTO;
import com.example.project_management.dto.ConstructionProjectDetailDTO;
import com.example.project_management.dto.ConstructionProjectSummaryDTO;
import com.example.project_management.model.ConstructionProject;
import com.example.project_management.repository.ConstructionProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConstructionProjectService {

    private final ConstructionProjectRepository constructionProjectRepository;
    private final MaterialCommunicationService materialCommunicationService;

    @Autowired
    public ConstructionProjectService(ConstructionProjectRepository constructionProjectRepository, MaterialCommunicationService materialCommunicationService) {
        this.constructionProjectRepository = constructionProjectRepository;
        this.materialCommunicationService = materialCommunicationService;
    }


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
        materialCommunicationService.notifyMaterialsAboutNewProject(project);
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
                .orElseThrow(() -> new RuntimeException("Project not found with id " + id)); // 404 Not Found

        project.setName(projectDTO.getName());
        project.setLocation(projectDTO.getLocation());
        materialCommunicationService.notifyMaterialsAboutUpdatedProject(project);
        constructionProjectRepository.save(project);
    }

    public boolean deleteProject(UUID projectId) {
        Optional<ConstructionProject> project = constructionProjectRepository.findById(projectId);


        if (project.isPresent()) {
            materialCommunicationService.notifyMaterialsToDelete(projectId);
            constructionProjectRepository.deleteById(projectId);
            return true;
        }

        return false;
    }

}
