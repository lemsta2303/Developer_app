package com.example.material_management.data;

import com.example.material_management.model.Material;
import com.example.material_management.model.SimplifiedProject;
import com.example.material_management.repository.MaterialRepository;
import com.example.material_management.repository.SimplifiedProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MaterialDataInitializer implements CommandLineRunner {

    private final MaterialRepository materialRepository;
    private final SimplifiedProjectRepository simplifiedProjectRepository;

    @Autowired
    public MaterialDataInitializer(MaterialRepository materialRepository, SimplifiedProjectRepository simplifiedProjectRepository) {
        this.materialRepository = materialRepository;
        this.simplifiedProjectRepository = simplifiedProjectRepository;
    }

    @Override
    public void run(String... args) {
        UUID project1Id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID project2Id = UUID.fromString("22222222-2222-2222-2222-222222222222");

        SimplifiedProject project1 = SimplifiedProject.builder()
                .id(project1Id)
                .name("Residential Building A")
                .build();

        SimplifiedProject project2 = SimplifiedProject.builder()
                .id(project2Id)
                .name("Office Complex B")
                .build();

        simplifiedProjectRepository.save(project1);
        simplifiedProjectRepository.save(project2);

        Material material1 = Material.builder()
                .id(UUID.randomUUID())
                .name("Concrete")
                .unit("kg")
                .unitPrice(50.0)
                .quantity(200)
                .project(project1)
                .build();

        Material material2 = Material.builder()
                .id(UUID.randomUUID())
                .name("Steel")
                .unit("ton")
                .unitPrice(3000.0)
                .quantity(10)
                .project(project2)
                .build();

        materialRepository.save(material1);
        materialRepository.save(material2);

        System.out.println("Initialized data for material-management with manual IDs.");
    }
}
