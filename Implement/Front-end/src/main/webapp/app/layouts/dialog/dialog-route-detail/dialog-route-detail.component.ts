import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Route } from './../../../entities/route';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'jhi-dialog-route-detail',
  templateUrl: './dialog-route-detail.component.html',
  styleUrls: ['./dialog-route-detail.component.scss'],
})
export class DialogRouteDetailComponent implements OnInit {
  route: Route;
  constructor(public dialogRef: MatDialogRef<DialogRouteDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.route = data.route;
  }

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
  route: Route;
}
