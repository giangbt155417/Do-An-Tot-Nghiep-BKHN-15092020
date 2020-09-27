import { BusStop } from 'app/entities/bus-stop';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

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
}

export interface DialogData {
  title: string;
  busStop: BusStop;
}
