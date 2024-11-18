package com.example.project_management.controller;

import com.example.project_management.dto.ConstructionProjectCreateUpdateDTO;
import com.example.project_management.dto.ConstructionProjectDetailDTO;
import com.example.project_management.dto.ConstructionProjectSummaryDTO;
import com.example.project_management.service.ConstructionProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            ConstructionProjectDetailDTO projectDetail = constructionProjectService.getProjectById(id);
            return ResponseEntity.ok(projectDetail); // 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 Not Found
        }
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
        try {
            constructionProjectService.updateProject(id, projectDTO);
            return ResponseEntity.ok().build(); // 200 OK
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //deleting project by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        boolean deleted = constructionProjectService.deleteProject(id);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

}
