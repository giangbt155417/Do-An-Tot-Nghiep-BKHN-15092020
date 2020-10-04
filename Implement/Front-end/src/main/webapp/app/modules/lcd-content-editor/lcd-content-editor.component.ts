import { Component, OnInit } from '@angular/core';
import { DialogProjectDetailComponent } from 'app/layouts/dialog/dialog-project-detail/dialog-project-detail.component';
import { DialogService } from 'app/services/dialog.service';
import { LCDTemplateGroup } from '../../entities/lcd-template-group';

@Component({
  selector: 'app-lcd-content-editor',
  templateUrl: './lcd-content-editor.component.html',
  styleUrls: ['./lcd-content-editor.component.scss'],
})
export class LcdContentEditorComponent implements OnInit {
  isNoData: boolean = false;
  isViewContents: boolean = false;
  isContentEditor: boolean = false;
  contentGroupSelected: any;
  contentSelected: any;
  contentGroupsView: Array<LCDTemplateGroup>;

  constructor(private dialogService: DialogService) {
    this.contentGroupsView = new Array<LCDTemplateGroup>();
    for (let index = 0; index < 10; index++) {
      let contentGroup = new LCDTemplateGroup();
      this.contentGroupsView.push(contentGroup);
    }
  }

  ngOnInit(): void {}

  public createContentGroup() {
    this.dialogService.showDialog(DialogProjectDetailComponent, { data: { title: 'Create Content Group' } }, (result: any) => {
      if (result) {
      }
    });
  }

  public editContentGroup() {
    this.dialogService.showDialog(
      DialogProjectDetailComponent,
      {
        data: {
          title: 'Edit Project',
          project: this.contentGroupSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteContentGroup() {}

  public selectContentGroup(contentGroup: any) {
    this.contentGroupSelected = contentGroup;
  }

  public viewContents() {
    this.isViewContents = true;
  }

  public backToContentGroups() {
    this.isViewContents = false;
    this.contentSelected = undefined;
    this.contentGroupSelected = undefined;
  }

  public createContent() {}

  public editContent() {}

  public deleteContent() {}

  public selectContent(content: any) {
    this.contentSelected = content;
  }

  public goToContentEditor(content: any) {
    this.isContentEditor = true;
  }
}
