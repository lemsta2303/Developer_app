import {Component, inject} from '@angular/core';
import {AddEditMaterialComponent} from "../project-details/add-edit-material/add-edit-material.component";
import {
  ProjectMaterialsContainerComponent
} from "../../templates/project-materials-container/project-materials-container.component";
import {MaterialDetailsModel} from "../../models/material-details.model";
import {ProjectDetailsModel} from "../../models/project-details.model";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {MaterialService} from "../../services/material.service";
import {ProjectService} from "../../services/project.service";

@Component({
  selector: 'app-material-details',
  standalone: true,
  imports: [
    AddEditMaterialComponent,
    ProjectMaterialsContainerComponent,
    RouterLink
  ],
  templateUrl: './material-details.component.html',
  styleUrl: './material-details.component.scss'
})
export class MaterialDetailsComponent {
  materialId!: string;
  projectId!: string;
  materialDetails: MaterialDetailsModel = {
    id: '',
    name: '',
    unit: '',
    unitPrice: 0,
    quantity: 0,
  };
  projectDetails: ProjectDetailsModel = {
    id: '',
    name: '',
    location: '',
  };

  private route = inject(ActivatedRoute);
  private materialService = inject(MaterialService);
  private projectService = inject(ProjectService);
  private router = inject(Router);

  ngOnInit(): void {
    this.projectId = this.route.snapshot.paramMap.get('id') || '';
    this.materialId = this.route.snapshot.paramMap.get('materialId') || '';

    if (this.projectId) {
      this.loadProjectDetails();
    } else {
      this.router.navigate(['/projects']);
    }

    if (this.materialId) {
      this.loadMaterialDetails();
    } else {
      this.router.navigate(['/project', this.projectId]);
    }
  }

  private loadProjectDetails(): void {
    this.projectService.getProjectById(this.projectId).subscribe((project) => {
      this.projectDetails = project;
    });
  }

  private loadMaterialDetails(): void {
    this.materialService.getMaterialById(this.materialId).subscribe((material) => {
      this.materialDetails = material;
    });
  }
}
