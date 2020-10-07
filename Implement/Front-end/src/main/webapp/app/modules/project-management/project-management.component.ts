import { PaginationInstance } from 'ngx-pagination';
import { Component, OnInit } from '@angular/core';
import { DialogProjectDetailComponent } from 'app/layouts/dialog/dialog-project-detail/dialog-project-detail.component';
import { DialogService } from 'app/services/dialog.service';
import { Project } from '../../entities/project';
import { ProjectManagementService } from 'app/services/project-management.service';
import { Constants } from 'app/utils/constants';
import { ActionService } from 'app/services/action.service';
import { DialogConfirmComponent } from 'app/layouts/dialog/dialog-confirm/dialog-confirm.component';

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
  totalProjects: number = 0;
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
    this.fetchDataProjects(this.pageFirst);
  }

  private fetchDataProjects(pageNumber: number, projectSelected?: Project) {
    this.loading = true;
    this.projectManagementService.countProjectsByUserId(1).subscribe(totalProjectsData => {
      this.totalProjects = totalProjectsData;
      this.pagingConfig.totalItems = this.totalProjects;
      if (this.totalProjects == 0) {
        this.isNoData = true;
      } else {
        this.isNoData = false;
      }
      this.projectManagementService.getProjectsByUserId(1, pageNumber).subscribe(
        projectsData => {
          this.projectsView = projectsData;
          this.loading = false;
          if (projectSelected) {
            this.selectProject(this.projectsView[this.projectsView.length - 1]);
          }
        },
        error => {
          console.log(error);
          return;
        },
        () => {
          this.loading = false;
        }
      );
    });
  }

  public createProject() {
    let projectNew = new Project('', 'admin', '', '', '');
    this.dialogService.showDialog(
      DialogProjectDetailComponent,
      {
        data: {
          title: 'Create Project',
          project: projectNew,
        },
      },
      result => {
        if (result) {
          this.projectManagementService.addProject(result).subscribe(projectNewData => {
            this.projectManagementService.countProjectsByUserId(1).subscribe(totalProjectsData => {
              this.totalProjects = totalProjectsData;
              this.pagingConfig.totalItems = this.totalProjects;
              let pageNumber = Math.ceil(this.pagingConfig.totalItems / Constants.RECORDS_PER_PAGE);
              console.log(this.totalProjects);
              console.log(pageNumber);
              if (pageNumber == this.pagingConfig.currentPage) {
                this.projectsView.push(projectNewData);
                this.selectProject(this.projectsView[this.projectsView.length - 1]);
              } else {
                this.onPageChange(pageNumber, projectNewData);
              }
            });
          });
        }
      }
    );
  }

  public editProject() {
    if (!this.projectSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogProjectDetailComponent,
      {
        data: {
          title: 'Edit Project',
          project: this.projectSelected,
        },
      },
      result => {
        if (result) {
          this.projectManagementService.editProject(result).subscribe(projectEditData => {
            let index = this.projectsView.findIndex(project => project.id == projectEditData.id);
            this.projectsView[index] = projectEditData;
            this.selectProject(this.projectsView[index]);
          });
        }
      }
    );
  }

  public deleteProject() {
    if (!this.projectSelected) {
      return;
    }
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.projectSelected.name} ?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      result => {
        if (result) {
          this.projectManagementService.deleteProject(this.projectSelected.id).subscribe(() => {
            this.projectSelected = undefined;
            this.onPageChange(this.pagingConfig.currentPage);
          });
        }
      }
    );
  }

  public selectProject(project: Project) {
    this.projectSelected = project;
  }

  public openProject(project: Project) {
    this.projectSelected = project;
    this.actionService.updateMenuState('[Action] Open Project');
    localStorage.setItem('projectId', this.projectSelected.id);
  }

  public onPageChange(pageNumber: any, projectSelected?: Project) {
    this.pagingConfig.currentPage = pageNumber;
    this.fetchDataProjects(this.pagingConfig.currentPage, projectSelected);
  }
}
