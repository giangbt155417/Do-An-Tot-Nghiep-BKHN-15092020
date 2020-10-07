import { Injectable } from '@angular/core';
import { Constants } from '../utils/constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BusStop } from '../entities/bus-stop';

@Injectable({
  providedIn: 'root',
})
export class BusStopManagementService {
  private readonly busStopApi = Constants.BACK_END_URL + Constants.PROGRAM_SERVICE + 'api/bus-stops';
  constructor(private http: HttpClient) {}

  getBusStops(pageNumber: number): Observable<BusStop[]> {
    return this.http.get<BusStop[]>(`${this.busStopApi}?pageNumber=${pageNumber}`);
  }

  countBusStops(): Observable<number> {
    return this.http.get<number>(`${this.busStopApi}/count`);
  }

  addBusStop(busStopNew: BusStop): Observable<BusStop> {
    return this.http.post<BusStop>(`${this.busStopApi}`, busStopNew);
  }

  editBusStop(busStopEdit: BusStop): Observable<BusStop> {
    return this.http.put<BusStop>(`${this.busStopApi}`, busStopEdit);
  }

  deleteBusStop(busStopId: Number): Observable<BusStop> {
    return this.http.delete<BusStop>(`${this.busStopApi}/${busStopId}`);
  }
}
