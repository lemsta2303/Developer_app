import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ProjectsListComponent} from "./components/projects-list/projects-list.component";
import {HttpClientModule} from "@angular/common/http";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ProjectsListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'developer-frontend';
}
