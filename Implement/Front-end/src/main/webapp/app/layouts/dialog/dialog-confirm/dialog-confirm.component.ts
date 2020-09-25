import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-confirm',
  templateUrl: './dialog-confirm.component.html',
  styleUrls: ['./dialog-confirm.component.scss'],
})
/**
 * Component class for Confirm and Error Dialog
 */
export class DialogConfirmComponent implements OnInit {
  constructor(public dialogRef: MatDialogRef<DialogConfirmComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  ngOnInit() {}
}
/**
 * Interface DialogData (title, text, button1, button2)
 */
export interface DialogData {
  text: string;
  button1: string;
  button2: string;
}
