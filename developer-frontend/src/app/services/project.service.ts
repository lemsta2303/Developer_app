import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProjectCreateUpdateModel} from "../models/project-create-update.model";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8085/api';

  private http = inject(HttpClient);

  getProjects(): Observable<any> {
    return this.http.get(this.baseUrl + '/projects')
  }

  deleteProject(id: string): Observable<void> {
    return this.http.delete<void>(this.baseUrl + '/projects/' + id);
  }

  createProject(project: ProjectCreateUpdateModel): Observable<any> {
    return this.http.post(this.baseUrl + '/projects', project);
  }

  updateProject(id: string, project: any): Observable<void> {
    return this.http.put<void>(this.baseUrl + '/projects/' + id, project);
  }
  getProjectById(id: string): Observable<any> {
    return this.http.get<any>(this.baseUrl + '/projects/' + id);
  }



}
