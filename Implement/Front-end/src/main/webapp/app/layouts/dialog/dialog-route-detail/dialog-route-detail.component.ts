import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Route } from './../../../entities/route';
import { Component, OnInit, Inject } from '@angular/core';
import * as moment from 'moment';
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

  save() {
    if (this.data.route.id) {
      this.dialogRef.close(this.data.route);
    } else {
      let currentDate = moment().format('YYYY-MM-DD');
      let routeNew = new Route(
        this.data.route.routeNo,
        this.data.route.name,
        this.data.route.suffix,
        currentDate,
        this.data.route.description
      );
      this.dialogRef.close(routeNew);
    }
  }
}

export interface DialogData {
  title: string;
  route: Route;
}
