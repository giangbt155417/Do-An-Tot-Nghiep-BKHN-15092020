import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'jhi-dialog-project-detail',
  templateUrl: './dialog-project-detail.component.html',
  styleUrls: ['./dialog-project-detail.component.scss'],
})
export class DialogProjectDetailComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogProjectDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
}
