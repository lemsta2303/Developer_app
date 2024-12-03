import {Component, inject, OnInit} from '@angular/core';
import {ProjectService} from "../../services/project.service";
import {ProjectSummaryModel} from "../../models/project-summary.model";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-projects-list',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './projects-list.component.html',
  styleUrl: './projects-list.component.scss'
})
export class ProjectsListComponent implements OnInit {
  projects: ProjectSummaryModel[] = [];
  private projectService = inject(ProjectService);

  ngOnInit() {
    this.loadProjects();
  }

  loadProjects() {
    this.projectService.getProjects().subscribe(projects => {
      this.projects = projects;
    })
  }

  deleteProject(projectId: string) {
    this.projectService.deleteProject(projectId).subscribe(() => {
      this.loadProjects();
    });

  }



}
