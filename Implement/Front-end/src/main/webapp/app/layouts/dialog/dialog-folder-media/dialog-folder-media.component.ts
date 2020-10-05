import { MediaFolder } from './../../../entities/media-folder';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'jhi-dialog-folder-media',
  templateUrl: './dialog-folder-media.component.html',
  styleUrls: ['./dialog-folder-media.component.scss'],
})
export class DialogFolderMediaComponent implements OnInit {
  folder: MediaFolder;
  constructor(public dialogRef: MatDialogRef<DialogFolderMediaComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.folder = data.folder;
  }

  ngOnInit(): void {}
}

export interface DialogData {
  title: string;
  folder: MediaFolder;
}
