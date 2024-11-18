package com.example.material_management.controller;

import com.example.material_management.dto.MaterialCreateUpdateDTO;
import com.example.material_management.dto.MaterialDetailDTO;
import com.example.material_management.dto.MaterialSummaryDTO;
import com.example.material_management.service.MaterialService;
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

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
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

}
