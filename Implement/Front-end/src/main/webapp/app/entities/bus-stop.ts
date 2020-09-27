import { NearbyPlace } from './nearby-place';

export class BusStop {
  id?: Number;
  busStopNo?: string;
  name?: string;
  suffix?: string;
  nearbyPlaces?: Array<NearbyPlace>;
  constructor() {}
}
