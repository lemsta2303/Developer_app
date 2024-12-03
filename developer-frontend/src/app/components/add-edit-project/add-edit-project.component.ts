import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ProjectService} from "../../services/project.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {
  ProjectMaterialsContainerComponent
} from "../../templates/project-materials-container/project-materials-container.component";

@Component({
  selector: 'app-add-edit-project',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink,
    ProjectMaterialsContainerComponent
  ],
  templateUrl: './add-edit-project.component.html',
  styleUrl: './add-edit-project.component.scss'
})
export class AddEditProjectComponent implements OnInit{
  projectForm: FormGroup;
  isEditMode = false;
  private projectId: string | null = null;
  oldStringName: string = '';

  private projectService = inject(ProjectService);
  private router = inject(Router);
  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);


  constructor() {
    this.projectForm = this.fb.group({
      name: ['', [Validators.required]],
      location: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.projectId = this.route.snapshot.paramMap.get('id');
    if (this.projectId) {
      this.isEditMode = true;
      this.loadProject();
    }
  }


  onSubmit() {
    if (this.isEditMode) {
      this.updateProject();
    } else {
      this.createProject();
    }
  }

  loadProject() {
    if (!this.projectId) return;
    this.projectService.getProjectById(this.projectId).subscribe((project) => {
      this.projectForm.patchValue({
        name: project.name,
        location: project.location
      });
      this.oldStringName = project.name;
    });
  }

  updateProject() {
    if (!this.projectId) return;
    this.projectService.updateProject(this.projectId, this.projectForm.value).subscribe(() => {
      this.router.navigate(['/projects']);
    });
  }

  createProject() {
    this.projectService.createProject(this.projectForm.value).subscribe(() => {
      this.router.navigate(['/projects']);
    });
  }

}
