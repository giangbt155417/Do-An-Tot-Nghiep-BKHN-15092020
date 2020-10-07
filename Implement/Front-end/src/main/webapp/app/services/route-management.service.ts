import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../utils/constants';
import { Observable } from 'rxjs';
import { Route } from '../entities/route';

@Injectable({
  providedIn: 'root',
})
export class RouteManagementService {
  private readonly routeApi = Constants.BACK_END_URL + Constants.PROGRAM_SERVICE + 'api/routes';
  constructor(private http: HttpClient) {}

  getRoutesByProjectId(projectId: Number, pageNumber: number): Observable<Route[]> {
    return this.http.get<Route[]>(`${this.routeApi}/project/${projectId}?pageNumber=${pageNumber}`);
  }

  countRoutesByProjectId(projectId: Number): Observable<number> {
    return this.http.get<number>(`${this.routeApi}/count/project/${projectId}`);
  }

  addRoute(routeNew: Route): Observable<Route> {
    return this.http.post<Route>(`${this.routeApi}`, routeNew);
  }

  editRoute(routeEdit: Route): Observable<Route> {
    return this.http.put<Route>(`${this.routeApi}`, routeEdit);
  }

  deleteRoute(routeId: Number): Observable<Route> {
    return this.http.delete<Route>(`${this.routeApi}/${routeId}`);
  }
}
