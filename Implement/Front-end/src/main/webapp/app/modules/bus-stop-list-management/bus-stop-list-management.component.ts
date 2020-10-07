import { BusStopManagementService } from './../../services/bus-stop-management.service';
import { Constants } from './../../utils/constants';
import { PaginationInstance } from 'ngx-pagination';
import { DialogConfirmComponent } from './../../layouts/dialog/dialog-confirm/dialog-confirm.component';
import { DialogBusStopDetailComponent } from './../../layouts/dialog/dialog-bus-stop-detail/dialog-bus-stop-detail.component';
import { DialogService } from './../../services/dialog.service';
import { BusStop } from './../../entities/bus-stop';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-bus-stop-list-management',
  templateUrl: './bus-stop-list-management.component.html',
  styleUrls: ['./bus-stop-list-management.component.scss'],
})
export class BusStopListManagementComponent implements OnInit {
  busStopSelected: any;
  busStopsView: Array<BusStop> = new Array<BusStop>();
  loading: boolean = false;
  pageFirst: number = 1;
  totalBusStops: number = 20;
  pagingConfig: PaginationInstance = {
    id: 'advanced',
    itemsPerPage: Constants.RECORDS_PER_PAGE,
    currentPage: this.pageFirst,
    totalItems: this.totalBusStops,
  };

  constructor(private dialogService: DialogService, private busStopManagementService: BusStopManagementService) {}

  ngOnInit(): void {
    this.fetchDataBusStops(this.pageFirst);
  }

  private fetchDataBusStops(pageNumber: number, busStopSelected?: BusStop) {
    this.loading = true;
    this.busStopManagementService.countBusStops().subscribe(totalBusStopsData => {
      this.totalBusStops = totalBusStopsData;
      this.pagingConfig.totalItems = this.totalBusStops;
      this.busStopManagementService.getBusStops(pageNumber).subscribe(
        busStopsData => {
          this.busStopsView = busStopsData;
          this.loading = false;
          if (busStopSelected) {
            this.selectBusStop(this.busStopsView[this.busStopsView.length - 1]);
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

  public createBusStop() {
    let busStopNew = new BusStop();
    this.dialogService.showDialog(
      DialogBusStopDetailComponent,
      {
        data: {
          title: 'Create Bus Stop',
          route: busStopNew,
        },
      },
      result => {
        if (result) {
          this.busStopManagementService.addBusStop(result).subscribe(busStopNewData => {
            this.busStopManagementService.countBusStops().subscribe(totalBusStopsData => {
              this.totalBusStops = totalBusStopsData;
              this.pagingConfig.totalItems = this.totalBusStops;
              let pageNumber = Math.ceil(this.pagingConfig.totalItems / Constants.RECORDS_PER_PAGE);
              if (pageNumber == this.pagingConfig.currentPage) {
                this.busStopsView.push(busStopNewData);
                this.selectBusStop(this.busStopsView[this.busStopsView.length - 1]);
              } else {
                this.onPageChange(pageNumber, busStopNewData);
              }
            });
          });
        }
      }
    );
  }

  public editBusStop() {
    if (!this.busStopSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogBusStopDetailComponent,
      {
        data: {
          title: 'Edit Bus Stop',
          route: this.busStopSelected,
        },
      },
      result => {
        if (result) {
          this.busStopManagementService.editBusStop(result).subscribe(busStopEditData => {
            let index = this.busStopsView.findIndex(route => route.id == busStopEditData.id);
            this.busStopsView[index] = busStopEditData;
            this.selectBusStop(this.busStopsView[index]);
          });
        }
      }
    );
  }

  public deleteBusStop() {
    if (!this.busStopSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.busStopSelected.name} ?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      result => {
        if (result) {
          this.busStopManagementService.deleteBusStop(this.busStopSelected.id).subscribe(() => {
            this.busStopSelected = undefined;
            this.onPageChange(this.pagingConfig.currentPage);
          });
        }
      }
    );
  }

  public selectBusStop(busStop: BusStop) {
    this.busStopSelected = busStop;
  }

  public onPageChange(pageNumber: any, busStopSelected?: BusStop) {
    this.pagingConfig.currentPage = pageNumber;
    this.fetchDataBusStops(this.pagingConfig.currentPage, busStopSelected);
  }
}
