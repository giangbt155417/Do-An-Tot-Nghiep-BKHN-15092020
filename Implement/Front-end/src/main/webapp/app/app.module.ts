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
import { DialogProjectDetailComponent } from './layouts/dialog/dialog-project-detail/dialog-project-detail.component';
import { DialogRouteDetailComponent } from './layouts/dialog/dialog-route-detail/dialog-route-detail.component';
import { DialogBusStopDetailComponent } from './layouts/dialog/dialog-bus-stop-detail/dialog-bus-stop-detail.component';
import { DialogNearByDetailComponent } from './layouts/dialog/dialog-near-by-detail/dialog-near-by-detail.component';
import { DialogFolderMediaComponent } from './layouts/dialog/dialog-folder-media/dialog-folder-media.component';
import { DialogMediaComponent } from './layouts/dialog/dialog-media/dialog-media.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AccessInterceptor } from '../app/blocks/interceptor/access.interceptor';

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
    NgxPaginationModule,
    HttpClientModule,
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
    ProjectManagementComponent,
    DialogConfirmComponent,
    DialogMessageComponent,
    DialogProjectDetailComponent,
    DialogRouteDetailComponent,
    DialogBusStopDetailComponent,
    DialogNearByDetailComponent,
    DialogFolderMediaComponent,
    DialogMediaComponent,
  ],
  entryComponents: [
    DialogConfirmComponent,
    DialogMessageComponent,
    DialogProjectDetailComponent,
    DialogRouteDetailComponent,
    DialogBusStopDetailComponent,
    DialogNearByDetailComponent,
    DialogFolderMediaComponent,
    DialogMediaComponent,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AccessInterceptor,
      multi: true,
    },
  ],
  bootstrap: [MainComponent],
})
export class GatewayAppModule {}
