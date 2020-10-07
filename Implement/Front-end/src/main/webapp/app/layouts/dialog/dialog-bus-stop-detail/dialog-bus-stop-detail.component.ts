import { BusStop } from 'app/entities/bus-stop';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'jhi-dialog-bus-stop-detail',
  templateUrl: './dialog-bus-stop-detail.component.html',
  styleUrls: ['./dialog-bus-stop-detail.component.scss'],
})
export class DialogBusStopDetailComponent implements OnInit {
  busStop: BusStop;
  constructor(public dialogRef: MatDialogRef<DialogBusStopDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.busStop = data.busStop;
  }

  ngOnInit(): void {}

  save() {
    if (this.data.busStop.id) {
      this.dialogRef.close(this.data.busStop);
    } else {
      let currentDate = moment().format('YYYY-MM-DD');
      let busStopNew = new BusStop(
        this.data.busStop.busStopNo,
        this.data.busStop.name,
        this.data.busStop.suffix,
        currentDate,
        this.data.busStop.description
      );
      this.dialogRef.close(busStopNew);
    }
  }
}

export interface DialogData {
  title: string;
  busStop: BusStop;
}
