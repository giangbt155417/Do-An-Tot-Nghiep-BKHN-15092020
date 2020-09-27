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
  busStopsView: Array<BusStop>;

  constructor(private dialogService: DialogService) {
    this.busStopsView = new Array<BusStop>();
    for (let index = 0; index < 10; index++) {
      let busStop = new BusStop('01', 'Hải Phòng', '01');
      this.busStopsView.push(busStop);
    }
  }

  ngOnInit(): void {}

  public createBusStop() {
    this.dialogService.showDialog(DialogBusStopDetailComponent, { data: { title: 'Create Bus Stop' } }, (result: any) => {
      if (result) {
      }
    });
  }

  public editBusStop() {
    this.dialogService.showDialog(
      DialogBusStopDetailComponent,
      {
        data: {
          title: 'Edit Bus Stop',
          busStop: this.busStopSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteBusStop() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.busStopSelected.name}?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      (result: any) => {
        if (result) {
          let index = this.busStopsView.findIndex(busStop => busStop.id == this.busStopSelected.id);
          this.busStopsView.splice(index, 1);
        }
      }
    );
  }

  public selectBusStop(busStop: BusStop) {
    this.busStopSelected = busStop;
  }
}
