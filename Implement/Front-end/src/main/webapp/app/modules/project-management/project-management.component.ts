import { Component, OnInit } from '@angular/core';
import { DialogProjectDetailComponent } from 'app/layouts/dialog/dialog-project-detail/dialog-project-detail.component';
import { DialogService } from 'app/services/dialog.service';
import { Project } from '../../entities/project';
@Component({
  selector: 'jhi-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.scss'],
})
export class ProjectManagementComponent implements OnInit {
  isNoData: boolean = false;
  projectSelected: any;
  projectsView: Array<Project>;

  constructor(private dialogService: DialogService) {
    this.projectsView = new Array<Project>();
    for (let index = 0; index < 10; index++) {
      let project = new Project('HUST PROJECT', 'GiangBT', '2020/09/25+7:00');
      this.projectsView.push(project);
    }
  }

  ngOnInit(): void {}

  public createProject() {
    this.dialogService.showDialog(DialogProjectDetailComponent, { data: { title: 'Create Project' } }, (result: any) => {
      if (result) {
      }
    });
  }

  public editProject() {}

  public deleteProject() {}

  public selectProject(project: Project) {
    this.projectSelected = project;
  }
}
