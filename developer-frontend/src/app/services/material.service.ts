import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MaterialService {

  private baseUrl = 'http://localhost:8085/api';
  private http = inject(HttpClient);

  getMaterialsByProject(projectId: string): Observable<any> {
    return this.http.get<any[]>(this.baseUrl + '/materials/project/' + projectId);
  }

  deleteMaterial(materialId: string): Observable<void> {
    return this.http.delete<void>(this.baseUrl + '/materials/' + materialId);
  }

  getMaterialById(materialId: string): Observable<any> {
    return this.http.get(this.baseUrl + '/materials/' + materialId);
  }

  createMaterial(material: any, projectId: string): Observable<any> {
    return this.http.post(this.baseUrl + '/materials/' + projectId, material);
  }

  updateMaterial(materialId: string, material: any): Observable<any> {
    return this.http.put(this.baseUrl + '/materials/' + materialId, material);
  }
}
