import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'jhi-dialog-message',
  templateUrl: './dialog-message.component.html',
  styleUrls: ['./dialog-message.component.scss'],
})
export class DialogMessageComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogMessageComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
  text: string;
}
