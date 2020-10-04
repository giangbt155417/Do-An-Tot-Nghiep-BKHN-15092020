import { Component, OnInit } from '@angular/core';
import { BusStop } from 'app/entities/bus-stop';
import { Route } from '../../entities/route';
@Component({
  selector: 'app-route-management',
  templateUrl: './route-management.component.html',
  styleUrls: ['./route-management.component.scss'],
})
export class RouteManagementComponent implements OnInit {
  routeSelected: any;
  routesView: Array<Route>;
  isViewBusStop: boolean = false;

  constructor() {
    this.routesView = new Array<Route>();
    for (let index = 0; index < 10; index++) {
      let route = new Route('01', 'Hà Nội - Hải Phòng', '01', new Array<BusStop>(), 1);
      this.routesView.push(route);
    }
  }

  ngOnInit(): void {}

  public createRoute() {}

  public editRoute() {}

  public deleteRoute() {}

  public selectRoute(route: Route) {
    this.routeSelected = route;
  }

  public viewBusStopOfRoute() {
    this.isViewBusStop = true;
  }

  public viewLCDPreviewOfRoute() {}

  public backToRouteList() {
    this.isViewBusStop = false;
  }
}
