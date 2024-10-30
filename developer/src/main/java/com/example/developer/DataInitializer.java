package com.example.developer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer {

    private final ConstructionProjectService constructionProjectService;
    private final MaterialService materialService;

    @Autowired
    public DataInitializer(ConstructionProjectService constructionProjectService, MaterialService materialService) {
        this.constructionProjectService = constructionProjectService;
        this.materialService = materialService;
    }

    @PostConstruct
    public void initData() {
        ConstructionProject amazingHouse = ConstructionProject.builder()
                .id(UUID.randomUUID())
                .name("House")
                .location("New York")
                .build();

        ConstructionProject universityBuilding = ConstructionProject.builder()
                .id(UUID.randomUUID())
                .name("University")
                .location("Los Angeles")
                .build();

        ConstructionProject schoolBuilding = ConstructionProject.builder()
                .id(UUID.randomUUID())
                .name("School")
                .location("Chicago")
                .build();

        constructionProjectService.save(amazingHouse);
        constructionProjectService.save(universityBuilding);
        constructionProjectService.save(schoolBuilding);

        Material wood = Material.builder()
                .id(UUID.randomUUID())
                .name("Wood")
                .unit("m")
                .unitPrice(0.3)
                .quantity(100)
                .project(amazingHouse)
                .build();

        Material bricks = Material.builder()
                .id(UUID.randomUUID())
                .name("Bricks")
                .unit("pcs")
                .unitPrice(0.5)
                .quantity(200)
                .project(amazingHouse)
                .build();

        Material cement = Material.builder()
                .id(UUID.randomUUID())
                .name("Cement")
                .unit("kg")
                .unitPrice(0.1)
                .quantity(1000)
                .project(universityBuilding)
                .build();

        Material steel = Material.builder()
                .id(UUID.randomUUID())
                .name("Steel")
                .unit("kg")
                .unitPrice(0.5)
                .quantity(500)
                .project(schoolBuilding)
                .build();

        materialService.save(wood);
        materialService.save(bricks);
        materialService.save(cement);
        materialService.save(steel);

    }
}
