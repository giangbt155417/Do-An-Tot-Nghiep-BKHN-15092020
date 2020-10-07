import { PaginationInstance } from 'ngx-pagination';
import { DialogConfirmComponent } from './../../layouts/dialog/dialog-confirm/dialog-confirm.component';
import { DialogRouteDetailComponent } from './../../layouts/dialog/dialog-route-detail/dialog-route-detail.component';
import { DialogService } from './../../services/dialog.service';
import { Component, OnInit } from '@angular/core';
import { Route } from '../../entities/route';
import { Constants } from 'app/utils/constants';
import { RouteManagementService } from 'app/services/route-management.service';

@Component({
  selector: 'app-route-management',
  templateUrl: './route-management.component.html',
  styleUrls: ['./route-management.component.scss'],
})
export class RouteManagementComponent implements OnInit {
  routeSelected: any;
  routesView: Array<Route> = new Array<Route>();
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

  constructor(private dialogService: DialogService, private routeManagementService: RouteManagementService) {}

  ngOnInit(): void {
    this.fetchDataRoutes(this.pageFirst);
  }

  private fetchDataRoutes(pageNumber: number, routeSelected?: Route) {
    this.loading = true;
    this.routeManagementService.countRoutesByProjectId(1).subscribe(totalRoutesData => {
      this.totalRoutes = totalRoutesData;
      this.pagingConfig.totalItems = this.totalRoutes;
      this.routeManagementService.getRoutesByProjectId(1, pageNumber).subscribe(
        routesData => {
          this.routesView = routesData;
          this.loading = false;
          if (routeSelected) {
            this.selectRoute(this.routesView[this.routesView.length - 1]);
          }
        },
        error => {
          console.log(error);
          return;
        },
        () => {
          this.loading = false;
        }
      );
    });
  }

  public createRoute() {
    let routeNew = new Route();
    this.dialogService.showDialog(
      DialogRouteDetailComponent,
      {
        data: {
          title: 'Create Route',
          route: routeNew,
        },
      },
      result => {
        if (result) {
          this.routeManagementService.addRoute(result).subscribe(routeNewData => {
            this.routeManagementService.countRoutesByProjectId(1).subscribe(totalRoutesData => {
              this.totalRoutes = totalRoutesData;
              this.pagingConfig.totalItems = this.totalRoutes;
              let pageNumber = Math.ceil(this.pagingConfig.totalItems / Constants.RECORDS_PER_PAGE);
              if (pageNumber == this.pagingConfig.currentPage) {
                this.routesView.push(routeNewData);
                this.selectRoute(this.routesView[this.routesView.length - 1]);
              } else {
                this.onPageChange(pageNumber, routeNewData);
              }
            });
          });
        }
      }
    );
  }

  public editRoute() {
    if (!this.routeSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogRouteDetailComponent,
      {
        data: {
          title: 'Edit Route',
          route: this.routeSelected,
        },
      },
      result => {
        if (result) {
          this.routeManagementService.editRoute(result).subscribe(routeEditData => {
            let index = this.routesView.findIndex(route => route.id == routeEditData.id);
            this.routesView[index] = routeEditData;
            this.selectRoute(this.routesView[index]);
          });
        }
      }
    );
  }

  public deleteRoute() {
    if (!this.routeSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.routeSelected.name} ?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      result => {
        if (result) {
          this.routeManagementService.deleteRoute(this.routeSelected.id).subscribe(() => {
            this.routeSelected = undefined;
            this.onPageChange(this.pagingConfig.currentPage);
          });
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

  public onPageChange(pageNumber: any, routeSelected?: Route) {
    this.pagingConfig.currentPage = pageNumber;
    this.fetchDataRoutes(this.pagingConfig.currentPage, routeSelected);
  }
}
