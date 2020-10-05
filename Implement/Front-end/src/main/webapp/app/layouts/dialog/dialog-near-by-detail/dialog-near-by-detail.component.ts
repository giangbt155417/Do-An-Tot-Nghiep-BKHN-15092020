import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NearbyPlace } from './../../../entities/nearby-place';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'jhi-dialog-near-by-detail',
  templateUrl: './dialog-near-by-detail.component.html',
  styleUrls: ['./dialog-near-by-detail.component.scss'],
})
export class DialogNearByDetailComponent implements OnInit {
  nearbyPlace: NearbyPlace;
  constructor(public dialogRef: MatDialogRef<DialogNearByDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.nearbyPlace = data.nearbyPlace;
  }

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
  nearbyPlace: NearbyPlace;
}
