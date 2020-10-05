import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';
import { Media } from '../../../entities/media';

@Component({
  selector: 'jhi-dialog-media',
  templateUrl: './dialog-media.component.html',
  styleUrls: ['./dialog-media.component.scss'],
})
export class DialogMediaComponent implements OnInit {
  media: Media;
  constructor(public dialogRef: MatDialogRef<DialogMediaComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.media = data.media;
  }

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
  media: Media;
}
