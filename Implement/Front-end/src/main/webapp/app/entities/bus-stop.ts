import { NearbyPlace } from './nearby-place';

export class BusStop {
  id?: Number;
  busStopNo?: string;
  name?: string;
  suffix?: string;
  createdDate?: string;
  description?: string;
  ID?: string;
  nearbyPlaces?: Array<NearbyPlace>;

  constructor(busStopNo?: string, name?: string, suffix?: string, createdDate?: string) {
    this.busStopNo = busStopNo;
    this.name = name;
    this.suffix = suffix;
    this.ID = `${this.busStopNo?.padStart(5, '0')}-${this.suffix?.padStart(3, '0')}`;
    this.createdDate = createdDate;
  }
}
