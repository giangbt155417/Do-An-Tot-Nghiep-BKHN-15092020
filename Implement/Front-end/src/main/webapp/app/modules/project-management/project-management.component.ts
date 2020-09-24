import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'jhi-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.scss'],
})
export class ProjectManagementComponent implements OnInit {
  isNoData: boolean = false;
  constructor() {}

  ngOnInit(): void {}
}
