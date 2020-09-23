import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ComponentType } from '@angular/cdk/portal';

@Injectable({
  providedIn: 'root',
})
export class DialogService {
  constructor(public dialog: MatDialog) {}

  showDialog<T>(dialogType: ComponentType<T>, config?: MatDialogConfig, onClosed?: (value: any) => void) {
    const dialogRef = this.dialog.open(dialogType, config);
    if (!onClosed) {
      return;
    }
    dialogRef.afterClosed().subscribe(onClosed);
  }
}
