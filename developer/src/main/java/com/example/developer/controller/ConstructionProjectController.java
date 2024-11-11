package com.example.developer.controller;

import com.example.developer.dto.ConstructionProjectCreateUpdateDTO;
import com.example.developer.dto.ConstructionProjectDetailDTO;
import com.example.developer.dto.ConstructionProjectSummaryDTO;
import com.example.developer.service.ConstructionProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
public class ConstructionProjectController {

    private final ConstructionProjectService constructionProjectService;

    @Autowired
    public ConstructionProjectController(ConstructionProjectService constructionProjectService) {
        this.constructionProjectService = constructionProjectService;
    }

    //creating new project
    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ConstructionProjectCreateUpdateDTO projectDTO) {
        constructionProjectService.createProject(projectDTO);
        return ResponseEntity.status(201).build();
    }

    //getting one project by id which is in url
    @GetMapping("/{id}")
    public ResponseEntity<ConstructionProjectDetailDTO> getProjectById(@PathVariable UUID id) {
        ConstructionProjectDetailDTO projectDetail = constructionProjectService.getProjectById(id);
        return ResponseEntity.ok(projectDetail); // 200 OK
    }

    //getting all projects
    @GetMapping
    public ResponseEntity<List<ConstructionProjectSummaryDTO>> getAllProjects() {
        List<ConstructionProjectSummaryDTO> projects = constructionProjectService.getAllProjects();
        return ResponseEntity.ok(projects); // 200 OK
    }

    //updating project by id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProject(
            @PathVariable UUID id,
            @RequestBody ConstructionProjectCreateUpdateDTO projectDTO) {
        constructionProjectService.updateProject(id, projectDTO);
        return ResponseEntity.ok().build(); // 200 OK
    }

    //deleting project by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        constructionProjectService.deleteProject(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
