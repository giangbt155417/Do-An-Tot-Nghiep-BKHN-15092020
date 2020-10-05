import { DialogMediaComponent } from './../../layouts/dialog/dialog-media/dialog-media.component';
import { VideoMedia } from './../../entities/video-media';
import { DialogFolderMediaComponent } from './../../layouts/dialog/dialog-folder-media/dialog-folder-media.component';
import { DialogConfirmComponent } from './../../layouts/dialog/dialog-confirm/dialog-confirm.component';
import { DialogService } from './../../services/dialog.service';
import { MediaFolder } from './../../entities/media-folder';
import { Component, OnInit } from '@angular/core';
import { Media } from '../../entities/media';

@Component({
  selector: 'app-media-management',
  templateUrl: './media-management.component.html',
  styleUrls: ['./media-management.component.scss'],
})
export class MediaManagementComponent implements OnInit {
  isNoData: boolean = false;
  folderSelected: any;
  foldersView: Array<MediaFolder>;
  isViewMediaList: boolean = false;
  mediaSelected: any;
  mediasOfFolder: Array<Media> = new Array<Media>();

  constructor(private dialogService: DialogService) {
    this.foldersView = new Array<MediaFolder>();
    for (let index = 0; index < 5; index++) {
      let id = index + 1;
      let folder = new MediaFolder(id, `Media ${id}`);
      folder.createDate = new Date();
      this.foldersView.push(folder);
    }
  }

  ngOnInit(): void {}
  public createFolderMedia() {
    this.dialogService.showDialog(
      DialogFolderMediaComponent,
      {
        data: {
          title: 'Create Folder Media',
          folder: new MediaFolder(),
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public editFolderMedia() {
    this.dialogService.showDialog(
      DialogFolderMediaComponent,
      {
        data: {
          title: 'Edit Folder Media',
          folder: this.folderSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteFolderMedia() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.folderSelected.name}?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      (result: any) => {
        if (result) {
          let index = this.foldersView.findIndex(folder => folder.id == this.folderSelected.id);
          this.foldersView.splice(index, 1);
        }
      }
    );
  }

  public selectFolder(folder: MediaFolder) {
    this.folderSelected = folder;
    this.mediasOfFolder = new Array<Media>();
    for (let index = 0; index < 10; index++) {
      let id = index + 1;
      let media = new VideoMedia(id, `media ${id}`, '../../../content/images/icon.png', this.folderSelected.id);
      this.mediasOfFolder.push(media);
    }
  }

  public viewMediaList() {
    this.isViewMediaList = true;
  }

  backToFolderList() {
    this.isViewMediaList = false;
  }

  importMedia() {}

  selectMedia(media: Media) {
    this.mediaSelected = media;
  }

  editMedia() {
    this.dialogService.showDialog(
      DialogMediaComponent,
      {
        data: {
          title: 'Edit Media',
          media: this.mediaSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  deleteMedia() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.mediaSelected.name}?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      (result: any) => {
        if (result) {
          let index = this.mediasOfFolder.findIndex(media => media.id == this.mediaSelected.id);
          this.mediasOfFolder.splice(index, 1);
        }
      }
    );
  }
}
