import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-project-materials-container',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule,
        RouterLink
    ],
  templateUrl: './project-materials-container.component.html',
  styleUrl: './project-materials-container.component.scss'
})
export class ProjectMaterialsContainerComponent {

}
