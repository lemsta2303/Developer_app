import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8085';

  private http = inject(HttpClient);

  getProjects(): Observable<any> {
    return this.http.get(this.baseUrl + '/projects')
  }




}
