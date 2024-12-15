import { Routes } from '@angular/router';
import {ProjectsListComponent} from "./components/projects-list/projects-list.component";
import {AddEditProjectComponent} from "./components/add-edit-project/add-edit-project.component";
import {ProjectDetailsComponent} from "./components/project-details/project-details.component";
import {MaterialDetailsComponent} from "./components/material-details/material-details.component";

export const routes: Routes = [
  { path: '', redirectTo: '/projects', pathMatch: 'full' },
  { path: 'projects', component: ProjectsListComponent },
  { path: 'add-project', component: AddEditProjectComponent },
  { path: 'edit-project/:id', component: AddEditProjectComponent },
  { path: 'project/:id', component: ProjectDetailsComponent },
  { path: 'project/:id/material/:materialId', component: MaterialDetailsComponent },

];
