import { BusStop } from './bus-stop';

export class Route {
  id?: Number;
  routeNo?: string;
  name?: string;
  suffix?: string;
  busStops?: Array<BusStop>;
  ID?: string;
  projectId?: Number;

  constructor(routeNo?: string, name?: string, suffix?: string, busStops?: Array<BusStop>, projectId?: Number) {
    this.routeNo = routeNo;
    this.name = name;
    this.suffix = suffix;
    this.busStops = busStops;
    this.ID = `${this.routeNo?.padStart(5, '0')}-${this.suffix?.padStart(3, '0')}`;
    this.projectId = projectId;
  }
}
