# Developer Application

## Description

The **Developer Application** is a tool for managing construction projects and materials, built using **Java Spring**. The application allows users to create, update, delete, and view projects and their associated materials.

This project is part of the course **"Architecture of Web Services"** and is still under development.

## API Endpoints

### Construction Projects
- **POST** `/api/projects`: Create a new project.
- **GET** `/api/projects/{projectId}`: Get project details by ID.
- **GET** `/api/projects`: Get all projects.
- **PUT** `/api/projects/{projectId}`: Update project by ID.
- **DELETE** `/api/projects/{projectId}`: Delete project by ID.

### Materials
- **POST** `/api/materials/{projectId}`: Create a new material for a project.
- **GET** `/api/materials/{materialId}`: Get material details by ID.
- **GET** `/api/materials`: Get all materials.
- **PUT** `/api/materials/{materialId}`: Update material by ID.
- **DELETE** `/api/materials/{materialId}`: Delete material by ID.

## Status

The project is under development. Future updates will include user authentication and enhanced features for managing materials.

## Technologies

- **Java Spring Boot** 
- **Spring Data JPA**
```

