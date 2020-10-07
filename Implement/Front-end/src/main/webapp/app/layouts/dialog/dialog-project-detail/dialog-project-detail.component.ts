import { Project } from './../../../entities/project';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import * as moment from 'moment';

@Component({
  selector: 'jhi-dialog-project-detail',
  templateUrl: './dialog-project-detail.component.html',
  styleUrls: ['./dialog-project-detail.component.scss'],
})
export class DialogProjectDetailComponent implements OnInit {
  project: Project;
  constructor(public dialogRef: MatDialogRef<DialogProjectDetailComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
    this.project = data.project;
  }

  ngOnInit(): void {}

  save() {
    if (this.data.project.id) {
      this.dialogRef.close(this.data.project);
    } else {
      let currentDate = moment().format('YYYY-MM-DD');
      let projectNew = new Project(
        this.data.project.name,
        this.data.project.owner,
        currentDate,
        this.data.project.expiryDate,
        this.data.project.description
      );
      this.dialogRef.close(projectNew);
    }
  }
}

export interface DialogData {
  title: string;
  project: Project;
}
