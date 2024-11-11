package com.example.developer.config;

import com.example.developer.model.ConstructionProject;
import com.example.developer.model.Material;
import com.example.developer.service.ConstructionProjectService;
import com.example.developer.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleCommandRunner implements CommandLineRunner {

    private final ConstructionProjectService constructionProjectService;
    private final MaterialService materialService;

    @Autowired
    public ConsoleCommandRunner(ConstructionProjectService constructionProjectService, MaterialService materialService) {
        this.constructionProjectService = constructionProjectService;
        this.materialService = materialService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Developer Application. Type 'help' to see available commands.");
        while (running) {
            System.out.print("\nEnter command: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "help":
                    printHelp();
                    break;
                case "list projects":
                    listProjects();
                    break;
                case "list materials":
                    listMaterials();
                    break;
                case "add material":
                    addMaterial(scanner);
                    break;
                case "delete material":
                    deleteMaterial(scanner);
                    break;
                case "exit":
                    System.out.println("Stopping the application...");
                    running = false;
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of commands.");
                    break;
            }
        }
        scanner.close();
    }

    private void printHelp() {
        System.out.println("\nAvailable commands:");
        System.out.println("help - List available commands");
        System.out.println("list projects - List all projects ");
        System.out.println("list materials - List all materials");
        System.out.println("add material - Add a new material with project selection");
        System.out.println("delete material - Delete an existing material by ID");
        System.out.println("exit - Stop the application");
    }

    private void listProjects() {
        List<ConstructionProject> projects = constructionProjectService.findAll();
        if (projects.isEmpty()) {
            System.out.println("No projects available.");
        } else {
            System.out.println("Available projects:");
            projects.forEach(project -> System.out.println(" - " + project.getName() + " (ID: " + project.getId() + ")"));
        }
    }

    private void listMaterials() {
        List<Material> materials = materialService.findAll();
        if (materials.isEmpty()) {
            System.out.println("No materials available.");
        } else {
            System.out.println("Available materials:");
            materials.forEach(material ->
                    System.out.println(" - " + material.getName() + " (ID: " + material.getId() + ", Project: " +
                            material.getProject().getName() + ")"));
        }
    }

    private void addMaterial(Scanner scanner) {
        System.out.println("Adding a new material...");
        System.out.print("Enter material name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter unit: ");
        String unit = scanner.nextLine().trim();

        System.out.print("Enter unit price: ");
        double unitPrice = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Select a project (enter ID):");
        listProjects();

        UUID projectId;
        try {
            projectId = UUID.fromString(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid project ID.");
            return;
        }

        ConstructionProject project = constructionProjectService.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Material newMaterial = Material.builder()
                .id(UUID.randomUUID())
                .name(name)
                .unit(unit)
                .unitPrice(unitPrice)
                .quantity(quantity)
                .project(project)
                .build();

        materialService.save(newMaterial);
        System.out.println("Material added successfully.");
    }

    private void deleteMaterial(Scanner scanner) {
        System.out.print("Enter the ID of the material to delete: ");
        UUID materialId;
        try {
            materialId = UUID.fromString(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid material ID.");
            return;
        }
        if (materialService.findById(materialId).isPresent()) {
            materialService.deleteById(materialId);
            System.out.println("Material deleted successfully.");
        } else {
            System.out.println("Material not found.");
        }
    }
}
