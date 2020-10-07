import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../utils/constants';
import { Observable } from 'rxjs';
import { Project } from '../entities/project';

@Injectable({
  providedIn: 'root',
})
export class ProjectManagementService {
  private readonly projectApi = Constants.BACK_END_URL + Constants.PROGRAM_SERVICE + 'api/projects';

  constructor(private http: HttpClient) {}

  getProjectsByUserId(userId: Number, pageNumber: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.projectApi}/user/${userId}?pageNumber=${pageNumber}`);
  }

  countProjectsByUserId(userId: Number): Observable<number> {
    return this.http.get<number>(`${this.projectApi}/count/user/${userId}`);
  }

  addProject(projectNew: Project): Observable<Project> {
    return this.http.post<Project>(`${this.projectApi}`, projectNew);
  }

  editProject(projectEdit: Project): Observable<Project> {
    return this.http.put<Project>(`${this.projectApi}`, projectEdit);
  }

  deleteProject(projectId: Number): Observable<Project> {
    return this.http.delete<Project>(`${this.projectApi}/${projectId}`);
  }
}
