import { AfterViewInit, ChangeDetectorRef, Component, ComponentFactoryResolver, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRouteSnapshot, NavigationEnd, NavigationError, Router } from '@angular/router';
import { AccountService } from 'app/core/auth/account.service';
import { LoginModalComponent } from 'app/shared/login/login.component';
import { UserManagementComponent } from 'app/modules/user-management/user-management.component';
import { RouteManagementComponent } from 'app/modules/route-management/route-management.component';
import { NearbyPlacesManagementComponent } from 'app/modules/nearby-places-management/nearby-places-management.component';
import { MediaManagementComponent } from 'app/modules/media-management/media-management.component';
import { LcdContentEditorComponent } from 'app/modules/lcd-content-editor/lcd-content-editor.component';
import { BusStopListManagementComponent } from 'app/modules/bus-stop-list-management/bus-stop-list-management.component';
import { ProjectManagementComponent } from 'app/modules/project-management/project-management.component';
import { FIELD_COMPONENT } from '../../utils/constants';
import { Subscription } from 'rxjs';
import { DialogService } from '../../services/dialog.service';
import { DialogConfirmComponent } from '../dialog/dialog-confirm/dialog-confirm.component';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
})
export class MainComponent implements OnInit, AfterViewInit {
  /**
   * entryComponents
   */
  entryComponents = [
    LoginModalComponent,
    ProjectManagementComponent,
    UserManagementComponent,
    RouteManagementComponent,
    NearbyPlacesManagementComponent,
    MediaManagementComponent,
    LcdContentEditorComponent,
    BusStopListManagementComponent,
  ];

  /**
   * ElementRef moduleLayout
   */
  @ViewChild('moduleLayout', {
    read: ViewContainerRef,
    static: true,
  })
  moduleLayout: any;

  componentRef: any;

  FIELD_COMPONENT = FIELD_COMPONENT;

  isHeader = false;

  userName: string = '';

  constructor(
    private accountService: AccountService,
    private titleService: Title,
    private router: Router,
    private componentFactoryResolver: ComponentFactoryResolver,
    private changeDetectorRef: ChangeDetectorRef,
    private dialogService: DialogService
  ) {}

  ngOnInit(): void {
    // try to log in automatically
    this.accountService.identity().subscribe();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateTitle();
      }
      if (event instanceof NavigationError && event.error.status === 404) {
        this.router.navigate(['/404']);
      }
    });
  }

  ngAfterViewInit(): void {
    // update component
    this.updateComponent();
  }

  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot): string {
    let title: string = routeSnapshot.data && routeSnapshot.data['pageTitle'] ? routeSnapshot.data['pageTitle'] : '';
    if (routeSnapshot.firstChild) {
      title = this.getPageTitle(routeSnapshot.firstChild) || title;
    }
    return title;
  }

  private updateTitle(): void {
    let pageTitle = this.getPageTitle(this.router.routerState.snapshot.root);
    if (!pageTitle) {
      pageTitle = 'Gateway';
    }
    this.titleService.setTitle(pageTitle);
  }

  private updateComponent() {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
    this.isHeader = false;
    const factory = this.componentFactoryResolver.resolveComponentFactory(LoginModalComponent);
    this.moduleLayout.clear();
    this.componentRef = this.moduleLayout.createComponent(factory);
    const sub: Subscription = this.componentRef.instance.loginSuccess.subscribe((username: any) => {
      this.userName = username;
      this.isHeader = true;
      this.moduleLayout.clear();
      this.changeDetectorRef.detectChanges();
    });
    this.componentRef.onDestroy(() => {
      sub.unsubscribe();
    });
    this.changeDetectorRef.detectChanges();
  }

  public selectComponent(component: any) {
    if (this.componentRef) {
      this.componentRef.destroy();
    }
    const factory = this.componentFactoryResolver.resolveComponentFactory(component);
    this.moduleLayout.clear();
    this.componentRef = this.moduleLayout.createComponent(factory);
    this.changeDetectorRef.detectChanges();
  }

  public signOut() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to log out?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      result => {
        if (result) {
          this.updateComponent();
        }
      }
    );
  }
}
