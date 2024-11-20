# Developer Application

## Description

The **Developer Application** is a tool for managing construction projects and materials, built using **Java Spring**. The application is developed using a **microservices architecture**, with separate services for project management and material management. An **API Gateway** is used to streamline communication between these services.  

This project is part of the course **"Architecture of Web Services"** and is still under development.

## API Endpoints

### Construction Projects

- **POST** `/api/projects`: Create a new project.  
- **GET** `/api/projects/{id}`: Retrieve details of a specific project.  
- **GET** `/api/projects`: Retrieve a list of all projects.  
- **PUT** `/api/projects/{id}`: Update an existing project.  
- **DELETE** `/api/projects/{id}`: Delete a project by its ID.  

### Materials

- **POST** `/api/materials/{projectId}`: Create a new material for a specific project.  
- **GET** `/api/materials/{id}`: Retrieve details of a specific material.  
- **GET** `/api/materials`: Retrieve a list of all materials.  
- **PUT** `/api/materials/{id}`: Update an existing material.  
- **DELETE** `/api/materials/{id}`: Delete a material by its ID.  

## Status

The project is under development. Future updates will include a front-end interface, user authentication, and enhanced features for managing materials.

## Technologies

- **Java Spring Boot**  
- **Spring Data JPA**  
- **Microservices Architecture**  
- **API Gateway**  
