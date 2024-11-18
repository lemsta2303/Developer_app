package com.example.project_management.service;


import com.example.project_management.dto.ConstructionProjectSummaryDTO;
import com.example.project_management.model.ConstructionProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class MaterialCommunicationService {

    private final RestTemplate restTemplate;

    @Autowired
    public MaterialCommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyMaterialsToDelete(UUID projectId) {
        restTemplate.delete("http://localhost:8082/api/materials/project/" + projectId);
    }

    public void notifyMaterialsAboutNewProject(ConstructionProject project) {
        String url = "http://localhost:8082/api/materials/project/" + project.getId();
        ConstructionProjectSummaryDTO dto = ConstructionProjectSummaryDTO.from(project);
        restTemplate.postForObject(url, dto, Void.class);
    }

    public void notifyMaterialsAboutUpdatedProject(ConstructionProject project) {
        String url = "http://localhost:8082/api/materials/project/" + project.getId();
        ConstructionProjectSummaryDTO dto = ConstructionProjectSummaryDTO.from(project);
        restTemplate.put(url, dto);
    }



}
