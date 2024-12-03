package com.example.material_management.service;

import com.example.material_management.dto.MaterialCreateUpdateDTO;
import com.example.material_management.dto.MaterialDetailDTO;
import com.example.material_management.dto.MaterialSummaryDTO;
import com.example.material_management.model.Material;
import com.example.material_management.model.SimplifiedProject;
import com.example.material_management.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final SimplifiedProjectService simplifiedProjectService;

//    private final ConstructionProjectService constructionProjectService;

//    @Autowired
//    public MaterialService(MaterialRepository materialRepository, ConstructionProjectService constructionProjectService) {
//        this.materialRepository = materialRepository;
//        this.constructionProjectService = constructionProjectService;
//    }

    @Autowired
    public MaterialService(MaterialRepository materialRepository, SimplifiedProjectService simplifiedProjectService) {
        this.materialRepository = materialRepository;
        this.simplifiedProjectService = simplifiedProjectService;
    }

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Optional<Material> findById(UUID id) {
        return materialRepository.findById(id);
    }

    public void save(Material material) {
        materialRepository.save(material);
    }

    public void deleteById(UUID id) {
        materialRepository.deleteById(id);
    }

    public List<Material> findByProject(UUID projectId) {
        SimplifiedProject project = simplifiedProjectService.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Simplified project not found with id " + projectId));
        return materialRepository.findByProject(project);
    }

    //methods for controller
    public void createMaterial(UUID projectId, MaterialCreateUpdateDTO materialDTO) {
        SimplifiedProject project = simplifiedProjectService.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Simplified project not found with id " + projectId));
        Material newMaterial = Material.builder()
                .id(UUID.randomUUID())
                .name(materialDTO.getName())
                .quantity(materialDTO.getQuantity())
                .unit(materialDTO.getUnit())
                .project(project)
                .build();
        materialRepository.save(newMaterial);
    }

    public MaterialDetailDTO getMaterialById(UUID id) {
        Material foundMaterial = findById(id)
                .orElseThrow(() -> new RuntimeException( "Material not found with id " + id));
        return MaterialDetailDTO.from(foundMaterial);
    }

    public List<MaterialSummaryDTO> getAllMaterials() {
        List<Material> materials = findAll();
        return materials.stream()
                .map(MaterialSummaryDTO::from)
                .collect(Collectors.toList());
    }

    public void updateMaterial(UUID id, MaterialCreateUpdateDTO materialDTO) {
        Material material = findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id " + id));
        material.setName(materialDTO.getName());
        material.setQuantity(materialDTO.getQuantity());
        material.setUnit(materialDTO.getUnit());
        save(material);
    }

    public boolean deleteMaterial(UUID id) {
        Optional<Material> material = findById(id);
        if (material.isPresent()) {
            deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public void deleteMaterialsByProjectId(UUID projectId) {
        SimplifiedProject project = simplifiedProjectService.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Simplified project not found with id " + projectId));
        List<Material> materials = materialRepository.findByProject(project);
        materials.forEach(material -> deleteById(material.getId()));

    }

    public List<MaterialSummaryDTO> getMaterialsByProjectId(UUID projectId) {
        SimplifiedProject project = simplifiedProjectService.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Simplified project not found with id " + projectId));
        List<Material> materials = materialRepository.findByProject(project);
        return materials.stream()
                .map(MaterialSummaryDTO::from)
                .collect(Collectors.toList());
    }




}
