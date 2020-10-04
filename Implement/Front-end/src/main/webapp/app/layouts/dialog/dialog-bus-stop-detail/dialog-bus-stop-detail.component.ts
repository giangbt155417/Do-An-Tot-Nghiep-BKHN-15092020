import { BusStop } from 'app/entities/bus-stop';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'jhi-dialog-bus-stop-detail',
  templateUrl: './dialog-bus-stop-detail.component.html',
  styleUrls: ['./dialog-bus-stop-detail.component.scss'],
})
export class DialogBusStopDetailComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogBusStopDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {}

  save() {}
}

export interface DialogData {
  title: string;
  busStop: BusStop;
}
