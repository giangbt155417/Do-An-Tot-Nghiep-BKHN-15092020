import { Component, OnInit } from '@angular/core';
import { DialogProjectDetailComponent } from 'app/layouts/dialog/dialog-project-detail/dialog-project-detail.component';
import { DialogService } from 'app/services/dialog.service';
import { Project } from '../../entities/project';
import { ProjectManagementService } from 'app/services/project-management.service';
import { PaginationInstance } from 'ngx-pagination';
import { Constants } from 'app/utils/constants';
import { ActionService } from 'app/services/action.service';

@Component({
  selector: 'jhi-project-management',
  templateUrl: './project-management.component.html',
  styleUrls: ['./project-management.component.scss'],
})
export class ProjectManagementComponent implements OnInit {
  isNoData: boolean = false;
  projectSelected: any;
  projectsView: Array<Project> = new Array<Project>();
  loading: boolean = false;
  pageFirst: number = 1;
  totalProjects: number = 20;
  pagingConfig: PaginationInstance = {
    id: 'advanced',
    itemsPerPage: Constants.RECORDS_PER_PAGE,
    currentPage: this.pageFirst,
    totalItems: this.totalProjects,
  };

  constructor(
    private dialogService: DialogService,
    private projectManagementService: ProjectManagementService,
    private actionService: ActionService
  ) {}

  ngOnInit(): void {
    this.projectManagementService.getProjectByUserId(1, 1).subscribe(
      projectsData => {
        this.projectsView = projectsData;
      },
      error => {
        console.log(error);
      }
    );
  }

  public createProject() {
    this.dialogService.showDialog(DialogProjectDetailComponent, { data: { title: 'Create Project' } }, (result: any) => {
      if (result) {
      }
    });
  }

  public editProject() {
    this.dialogService.showDialog(
      DialogProjectDetailComponent,
      {
        data: {
          title: 'Edit Project',
          project: this.projectSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteProject() {}

  public selectProject(project: Project) {
    this.projectSelected = project;
  }

  public openProject(project: Project) {
    this.projectSelected = project;
    this.actionService.updateMenuState('[Action] Open Project');
  }

  public onPageChange(pageNumber: any) {}
}
