package com.example.project_management.data;

import com.example.project_management.model.ConstructionProject;
import com.example.project_management.repository.ConstructionProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectDataInitializer implements CommandLineRunner {

    private final ConstructionProjectRepository constructionProjectRepository;

    @Autowired
    public ProjectDataInitializer(ConstructionProjectRepository constructionProjectRepository) {
        this.constructionProjectRepository = constructionProjectRepository;
    }

    @Override
    public void run(String... args) {
        UUID project1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID project2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

        ConstructionProject project1 = ConstructionProject.builder()
                .id(project1Id)
                .name("Residential Building A")
                .location("New York")
                .build();

        ConstructionProject project2 = ConstructionProject.builder()
                .id(project2Id)
                .name("Office Complex B")
                .location("San Francisco")
                .build();

        constructionProjectRepository.save(project1);
        constructionProjectRepository.save(project2);

        System.out.println("Initialized data for project-management with manual IDs.");
    }
}
