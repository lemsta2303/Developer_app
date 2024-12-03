import {Component, inject, Input, input, OnInit, Output, output} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {
  ProjectMaterialsContainerComponent
} from "../../../templates/project-materials-container/project-materials-container.component";
import { EventEmitter } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MaterialService} from "../../../services/material.service";

@Component({
  selector: 'app-add-edit-material',
  standalone: true,
  imports: [
    FormsModule,
    ProjectMaterialsContainerComponent,
    ReactiveFormsModule
  ],
  templateUrl: './add-edit-material.component.html',
  styleUrl: './add-edit-material.component.scss'
})
export class AddEditMaterialComponent{
  @Input () ifOpened!: boolean;
  @Output() ifOpenedChange = new EventEmitter<boolean>();
  refreshMaterialsList = output();

  materialForm!: FormGroup;
  isEditMode = false;
  projectId!: string;
  materialId: string | null = null;




  private fb = inject(FormBuilder);
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private materialService = inject(MaterialService);

  constructor() {
    this.materialForm = this.fb.group({
      name: ['', [Validators.required]],
      unit: ['', [Validators.required]],
      unitPrice: [null, [Validators.required, Validators.min(0)]],
      quantity: [null, [Validators.required, Validators.min(1)]]
    });
  }

  initializePopup() {
    this.route.queryParamMap.subscribe((params) => {
      this.projectId = this.route.snapshot.paramMap.get('id') || '';
      this.materialId = params.get('materialId');
      if (this.materialId) {
        this.isEditMode = true;
        this.loadMaterial();
      } else {
        this.isEditMode = false;
        this.materialForm.reset();
      }
    });
  }

  closePopup() {
    this.ifOpened = false;
    this.ifOpenedChange.emit(false);
    this.router.navigate(['project', this.projectId]);
  }

  onSubmit() {
    if (this.materialForm.invalid) return;

    if (this.isEditMode) {
      this.updateMaterial();
    } else {
      this.addMaterial();
    }
    this.resetForm();
  }

  private loadMaterial() {
    if (!this.materialId) return;

    this.materialService.getMaterialById(this.materialId).subscribe(material => {
      this.materialForm.patchValue({
        name: material.name,
        unit: material.unit,
        unitPrice: material.unitPrice,
        quantity: material.quantity
      });
    });
  }

  private addMaterial() {
    const newMaterial = {
      name: this.materialForm.value.name,
      unit: this.materialForm.value.unit,
      unitPrice: this.materialForm.value.unitPrice,
      quantity: this.materialForm.value.quantity
    };

    this.materialService.createMaterial(newMaterial, this.projectId).subscribe(() => {
      this.closePopup();
      this.refreshMaterialsList.emit();
    });
  }

  private updateMaterial() {
    if (!this.materialId) return;

    this.materialService.updateMaterial(this.materialId, this.materialForm.value).subscribe(() => {
      this.closePopup();
      this.refreshMaterialsList.emit();
    });
  }

  resetForm() {
    this.materialForm.reset();
    this.isEditMode = false;
    this.materialId = null;
  }


}
