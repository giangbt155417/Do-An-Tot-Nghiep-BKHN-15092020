import { BusStop } from './bus-stop';

export class Route {
  id?: Number;
  routeNo?: string;
  name?: string;
  suffix?: string;
  projectId?: Number;
  createdDate?: string;
  description?: string;
  busStops?: Array<BusStop>;
  ID?: string;

  constructor(
    routeNo?: string,
    name?: string,
    suffix?: string,
    projectId?: Number,
    createdDate?: string,
    description?: string,
    busStops?: Array<BusStop>
  ) {
    this.routeNo = routeNo;
    this.name = name;
    this.suffix = suffix;
    this.projectId = projectId;
    this.createdDate = createdDate;
    this.description = description;
    this.busStops = busStops;
    this.ID = `${this.routeNo?.padStart(5, '0')}-${this.suffix?.padStart(3, '0')}`;
  }
}
