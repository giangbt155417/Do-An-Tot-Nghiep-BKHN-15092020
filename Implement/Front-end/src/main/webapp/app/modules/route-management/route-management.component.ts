import { DialogConfirmComponent } from './../../layouts/dialog/dialog-confirm/dialog-confirm.component';
import { DialogRouteDetailComponent } from './../../layouts/dialog/dialog-route-detail/dialog-route-detail.component';
import { DialogService } from './../../services/dialog.service';
import { Component, OnInit } from '@angular/core';
import { BusStop } from 'app/entities/bus-stop';
import { Route } from '../../entities/route';
import { PaginationInstance } from 'ngx-pagination';
import { Constants } from 'app/utils/constants';
import { RouteManagementService } from 'app/services/route-management.service';

@Component({
  selector: 'app-route-management',
  templateUrl: './route-management.component.html',
  styleUrls: ['./route-management.component.scss'],
})
export class RouteManagementComponent implements OnInit {
  routeSelected: any;
  routesView: Array<Route>;
  isViewBusStop: boolean = false;
  loading: boolean = false;
  pageFirst: number = 1;
  totalRoutes: number = 20;
  pagingConfig: PaginationInstance = {
    id: 'advanced',
    itemsPerPage: Constants.RECORDS_PER_PAGE,
    currentPage: this.pageFirst,
    totalItems: this.totalRoutes,
  };

  constructor(private dialogService: DialogService, private routeManagementService: RouteManagementService) {
    this.routesView = new Array<Route>();
    for (let index = 0; index < 10; index++) {
      let route = new Route('01', 'Hà Nội - Hải Phòng', '01', 1, '20200708', '', new Array<BusStop>());
      this.routesView.push(route);
    }
  }

  ngOnInit(): void {}

  public createRoute() {
    this.dialogService.showDialog(
      DialogRouteDetailComponent,
      {
        data: {
          title: 'Create Route',
          route: new Route(),
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public editRoute() {
    this.dialogService.showDialog(
      DialogRouteDetailComponent,
      {
        data: {
          title: 'Edit Bus Stop',
          route: this.routeSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteRoute() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.routeSelected.name}?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      (result: any) => {
        if (result) {
          let index = this.routesView.findIndex(route => route.id == this.routeSelected.id);
          this.routesView.splice(index, 1);
        }
      }
    );
  }

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

  public onPageChange(pageNumber: any) {}
}
