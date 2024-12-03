import {Component, inject, OnInit, ViewChild} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {
  ProjectMaterialsContainerComponent
} from "../../templates/project-materials-container/project-materials-container.component";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ProjectService} from "../../services/project.service";
import {MaterialService} from "../../services/material.service";
import {MaterialSummaryModel} from "../../models/material-summary.model";
import {ProjectDetailsModel} from "../../models/project-details.model";
import {AddEditMaterialComponent} from "./add-edit-material/add-edit-material.component";

@Component({
  selector: 'app-project-details',
  standalone: true,
  imports: [
    FormsModule,
    ProjectMaterialsContainerComponent,
    ReactiveFormsModule,
    RouterLink,
    AddEditMaterialComponent
  ],
  templateUrl: './project-details.component.html',
  styleUrl: './project-details.component.scss'
})
export class ProjectDetailsComponent implements OnInit{
  projectId!: string;
  projectDetails: ProjectDetailsModel = {
    id: '',
    name: '',
    location: ''
  }
  materials: MaterialSummaryModel[] = [];

  private route = inject(ActivatedRoute);
  private projectService = inject(ProjectService);
  private materialService = inject(MaterialService);
  private router = inject(Router);

  popupOpened = false;

  @ViewChild('materialPopup') materialPopup!: AddEditMaterialComponent;




  ngOnInit() {
    this.projectId = this.route.snapshot.paramMap.get('id')!;
    if (this.projectId) {
      this.loadProjectDetails();
      this.loadMaterials();
    } else {
      this.router.navigate(['/projects']);
    }
  }

  loadProjectDetails() {
    this.projectService.getProjectById(this.projectId).subscribe((project) => {
      this.projectDetails = project;
    });
  }

  loadMaterials() {
    this.materialService.getMaterialsByProject(this.projectId).subscribe((materials) => {
      this.materials = materials;
    });
  }

  deleteMaterial(materialId: string) {
    this.materialService.deleteMaterial(materialId).subscribe( () => {
      this.loadMaterials();
    })
  }

  editMaterial(materialId: string) {

    const currentUrl = this.router.url.split('?')[0]; // Aktualna ścieżka bez parametrów
    this.router.navigate([currentUrl], {
      queryParams: { materialId }
    });
    this.openAddEditMaterialPopup();

  }


  openAddEditMaterialPopup() {
    this.popupOpened = true;
    this.materialPopup.initializePopup();
  }
}
