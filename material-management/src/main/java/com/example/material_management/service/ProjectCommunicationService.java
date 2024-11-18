package com.example.material_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectCommunicationService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProjectCommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


}
