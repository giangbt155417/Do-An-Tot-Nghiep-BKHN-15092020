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

  getProjectByUserId(userId: Number, pageNumber: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.projectApi}/user/${userId}?pageNumber=${pageNumber}`);
  }
}
