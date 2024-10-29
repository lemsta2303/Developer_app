package com.example.developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@SpringBootApplication
public class DeveloperApplication {

	public static void main(String[] args) {

		SpringApplication.run(DeveloperApplication.class, args);

		// ex.2 data creating
		ConstructionProject amazingHouse = ConstructionProject.builder()
				.name("House")
				.location("New York")
				.materials(List.of(
						Material.builder()
								.name("Wood")
								.unit("m")
								.unitPrice(0.3)
								.quantity(100)
								.build(),
						Material.builder()
								.name("Bricks")
								.unit("pcs")
								.unitPrice(0.5)
								.quantity(200)
								.build(),
						Material.builder()
								.name("Cement")
								.unit("kg")
								.unitPrice(0.1)
								.quantity(1000)
								.build()
				))
				.build();

		ConstructionProject universityBuilding = ConstructionProject.builder()
				.name("University")
				.location("Los Angeles")
				.materials(List.of(
						Material.builder()
								.name("Electric wires")
								.unit("m")
								.unitPrice(0.2)
								.quantity(1000)
								.build(),
						Material.builder()
								.name("Concrete")
								.unit("m3")
								.unitPrice(100)
								.quantity(100)
								.build()
				))
				.build();

		ConstructionProject schoolBuilding = ConstructionProject.builder()
				.name("School")
				.location("Chicago")
				.materials(List.of(
						Material.builder()
								.name("steel")
								.unit("kg")
								.unitPrice(0.5)
								.quantity(1000)
								.build(),
						Material.builder()
								.name("Concrete")
								.unit("m3")
								.unitPrice(100)
								.quantity(100)
								.build()
				))
				.build();

		List<ConstructionProject> constructionProjects = List.of(amazingHouse, universityBuilding, schoolBuilding);

		// ex.2 data printing
		System.out.println("\nex.2");
		printAllConstructionProjects(constructionProjects);

		// ex. 3 collecting all materials into a set
		System.out.println("\nex.3");
		Set<Material> allMaterials = collectAllMaterials(constructionProjects);
		allMaterials.forEach(System.out::println);

		// ex. 4 pipeline filter elements collection
		System.out.println("\nex.4");
		List<Material> filteredMaterials = filterMaterials(allMaterials.stream().toList(), 150);
		filteredMaterials.forEach(System.out::println);

		// ex.5  transform to DTOs, sort by natural order and collect into a List
		System.out.println("\nex.5");
		List<MaterialDto> materialDtos = transformToMaterialDtos(allMaterials);
		materialDtos.forEach(System.out::println);

		// ex. 6 projects serialization and deserialization
		System.out.println("\nex.6");
		serializeProjects(constructionProjects);
		List<ConstructionProject> deserializedProjects = deserializeProjects("projects.bin");
		printAllConstructionProjects(deserializedProjects);

		// ex. 7 threads
		System.out.println("\nex.7");
		processProjectsInParallel(constructionProjects, 2);

	}

	public static void printConstructionProject(ConstructionProject constructionProject) {
		System.out.println(constructionProject);
		constructionProject.getMaterials().forEach(material -> {
			System.out.println("  - " + material);
		});
	}

	public static void printAllConstructionProjects(List<ConstructionProject> constructionProjects) {
		for (ConstructionProject constructionProject : constructionProjects) {
			printConstructionProject(constructionProject);
		}
	}

	public static Set<Material> collectAllMaterials(List<ConstructionProject> constructionProjects) {

		return constructionProjects.stream()
				.flatMap(constructionProject -> constructionProject.getMaterials().stream())
				.collect(Collectors.toSet());
	}

	public static List<Material> filterMaterials(List<Material> materials, double quantity) {
		return materials.stream()
				.filter(material -> material.getQuantity() > quantity)
				.sorted(Comparator.comparing(Material::getName))
				.collect(Collectors.toList());
	}

	public static List<MaterialDto> transformToMaterialDtos(Collection<Material> materials) {
		return materials.stream()
				.map(material -> MaterialDto.builder()
						.name(material.getName())
						.unit(material.getUnit())
						.unitPrice(material.getUnitPrice())
						.quantity(material.getQuantity())
						.build())
				.sorted()
				.toList();
	}

	public static void serializeProjects(List<ConstructionProject> constructionProjects) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("projects.bin"))) {
			oos.writeObject(constructionProjects);
		} catch (IOException e) {
			System.out.println("Serialization failed");
		}
	}

	public static List<ConstructionProject> deserializeProjects(String path) {
		List<ConstructionProject> deserializedProjects = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			deserializedProjects = (List<ConstructionProject>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Deserialization failed");
		}
		return deserializedProjects;
	}

	public static void processProjectsInParallel(List<ConstructionProject> projects, int poolSize) {
		ForkJoinPool customThreadPool = new ForkJoinPool(poolSize);
		try {
			customThreadPool.submit(() ->
					projects.parallelStream()
							.forEach(project -> {
								System.out.println("Project: " + project.getName() +
										" (Thread: " + Thread.currentThread().getName() + ")");
								project.getMaterials().forEach(material -> {
									try {
										Thread.sleep(500);
										System.out.println("  Material: " + material +
												" (Thread: " + Thread.currentThread().getName() + ")");
									} catch (InterruptedException e) {
										Thread.currentThread().interrupt();
										System.out.println("Interrupted: " + e.getMessage());
									}
								});
							})
			).get();
		} catch (Exception e) {
			System.out.println("Error during parallel execution: " + e.getMessage());
		} finally {
			customThreadPool.shutdown();
		}
	}

}
