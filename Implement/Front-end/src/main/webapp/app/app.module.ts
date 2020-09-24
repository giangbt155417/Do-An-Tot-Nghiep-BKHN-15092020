import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import './vendor';
import { GatewaySharedModule } from 'app/shared/shared.module';
import { GatewayCoreModule } from 'app/core/core.module';
import { GatewayAppRoutingModule } from './app-routing.module';
import { GatewayHomeModule } from './home/home.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { ErrorComponent } from './layouts/error/error.component';
import { RouteManagementComponent } from './modules/route-management/route-management.component';
import { MediaManagementComponent } from './modules/media-management/media-management.component';
import { UserManagementComponent } from './modules/user-management/user-management.component';
import { LcdContentEditorComponent } from './modules/lcd-content-editor/lcd-content-editor.component';
import { NearbyPlacesManagementComponent } from './modules/nearby-places-management/nearby-places-management.component';
import { BusStopListManagementComponent } from './modules/bus-stop-list-management/bus-stop-list-management.component';
import { DialogConfirmComponent } from './layouts/dialog/dialog-confirm/dialog-confirm.component';
import { DialogMessageComponent } from './layouts/dialog/dialog-message/dialog-message.component';
import { MatDialogModule } from '@angular/material/dialog';
import { ProjectManagementComponent } from './modules/project-management/project-management.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    GatewaySharedModule,
    GatewayCoreModule,
    GatewayHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    GatewayAppRoutingModule,
    MatDialogModule,
  ],
  declarations: [
    MainComponent,
    ErrorComponent,
    RouteManagementComponent,
    MediaManagementComponent,
    UserManagementComponent,
    LcdContentEditorComponent,
    NearbyPlacesManagementComponent,
    BusStopListManagementComponent,
    DialogConfirmComponent,
    DialogMessageComponent,
    ProjectManagementComponent,
  ],
  entryComponents: [DialogConfirmComponent, DialogMessageComponent],
  bootstrap: [MainComponent],
})
export class GatewayAppModule {}
