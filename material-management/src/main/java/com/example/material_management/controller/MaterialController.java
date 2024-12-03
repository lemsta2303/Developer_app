package com.example.material_management.controller;

import com.example.material_management.dto.MaterialCreateUpdateDTO;
import com.example.material_management.dto.MaterialDetailDTO;
import com.example.material_management.dto.MaterialSummaryDTO;
import com.example.material_management.model.SimplifiedProject;
import com.example.material_management.service.MaterialService;
import com.example.material_management.service.SimplifiedProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final SimplifiedProjectService simplifiedProjectService;

    @Autowired
    public MaterialController(MaterialService materialService, SimplifiedProjectService simplifiedProjectService) {
        this.materialService = materialService;
        this.simplifiedProjectService = simplifiedProjectService;
    }

    // creating new material
    @PostMapping("/{projectId}")
    public ResponseEntity<Void> createMaterial(
            @PathVariable UUID projectId,
            @RequestBody MaterialCreateUpdateDTO materialDTO) {
        try {
            materialService.createMaterial(projectId, materialDTO);
            return ResponseEntity.status(201).build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // 404 Not Found
        }
    }

    // get material by id
    @GetMapping("/{id}")
    public ResponseEntity<MaterialDetailDTO> getMaterialById(@PathVariable UUID id) {
        try {
            MaterialDetailDTO materialDetailDTO = materialService.getMaterialById(id);
            return ResponseEntity.ok(materialDetailDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // get material by project id
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<MaterialSummaryDTO>> getMaterialsByProjectId(@PathVariable UUID projectId) {
        try {
            List<MaterialSummaryDTO> materials = materialService.getMaterialsByProjectId(projectId);
            return ResponseEntity.ok(materials);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //get all materials
    @GetMapping
    public ResponseEntity<List<MaterialSummaryDTO>> getAllMaterials() {
        List<MaterialSummaryDTO> materials = materialService.getAllMaterials();
        return ResponseEntity.ok(materials);
    }

    //update material by id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMaterial(
            @PathVariable UUID id,
            @RequestBody MaterialCreateUpdateDTO materialDTO) {
        try {
            materialService.updateMaterial(id, materialDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //delete material by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable UUID id) {
        boolean deleted = materialService.deleteMaterial(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //delete materials when some project is deleted to synchronous data
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<Void> deleteMaterialsByProjectId(@PathVariable UUID projectId) {
        materialService.deleteMaterialsByProjectId(projectId);
        simplifiedProjectService.deleteSimplifiedProject(projectId);
        return ResponseEntity.noContent().build();
    }

    //adding simplified project to synchronous data
    @PostMapping("/project/{projectId}")
    public ResponseEntity<Void> addSimplifiedProject(
            @PathVariable UUID projectId,
            @RequestBody SimplifiedProject projectDTO) {
        try {
            simplifiedProjectService.createSimplifiedProject(projectId, projectDTO.getName());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //updating simplified project to synchronous data
    @PutMapping("/project/{projectId}")
    public ResponseEntity<Void> updateSimplifiedProject(
            @PathVariable UUID projectId,
            @RequestBody SimplifiedProject projectDTO) {
        try {
            simplifiedProjectService.updateSimplifiedProject(projectId, projectDTO.getName());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
