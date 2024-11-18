package com.example.project_management.service;

import com.example.project_management.dto.MaterialSummaryDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class MaterialServiceClient {

    private final RestTemplate restTemplate;

    @Value("${material.management.url}")
    private String materialManagementUrl;

    public MaterialServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Metoda do pobierania materiałów powiązanych z projektem
    public List<MaterialSummaryDTO> getMaterialsByProjectId(UUID projectId) {
        String url = materialManagementUrl + "/projects/" + projectId + "/materials";
        MaterialSummaryDTO[] materials = restTemplate.getForObject(url, MaterialSummaryDTO[].class);
        return List.of(materials);  // Zwracamy listę materiałów
    }
}
