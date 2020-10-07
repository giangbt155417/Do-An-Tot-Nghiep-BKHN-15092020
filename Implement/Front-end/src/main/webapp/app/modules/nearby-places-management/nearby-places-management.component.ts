import { DialogConfirmComponent } from './../../layouts/dialog/dialog-confirm/dialog-confirm.component';
import { NearbyPlace } from './../../entities/nearby-place';
import { Component, OnInit } from '@angular/core';
import { DialogService } from 'app/services/dialog.service';
import { DialogNearByDetailComponent } from 'app/layouts/dialog/dialog-near-by-detail/dialog-near-by-detail.component';

@Component({
  selector: 'app-nearby-places-management',
  templateUrl: './nearby-places-management.component.html',
  styleUrls: ['./nearby-places-management.component.scss'],
})
export class NearbyPlacesManagementComponent implements OnInit {
  nearbyPlaceSelected: any;
  nearbyPlacesView: Array<NearbyPlace>;

  constructor(private dialogService: DialogService) {
    this.nearbyPlacesView = new Array<NearbyPlace>();
    for (let index = 0; index < 2; index++) {
      let id = index + 1;
      let place = new NearbyPlace(id, 'KFC', '', 'Chicken fry');
      this.nearbyPlacesView.push(place);
    }
  }

  ngOnInit(): void {}

  public createNearbyPlace() {
    this.dialogService.showDialog(
      DialogNearByDetailComponent,
      {
        data: {
          title: 'Create Nearby Place',
          nearbyPlace: new NearbyPlace(),
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public editNearbyPlace() {
    this.dialogService.showDialog(
      DialogNearByDetailComponent,
      {
        data: {
          title: 'Edit Nearby Place',
          nearbyPlace: this.nearbyPlaceSelected,
        },
      },
      (result: any) => {
        if (result) {
        }
      }
    );
  }

  public deleteNearbyPlace() {
    this.dialogService.showDialog(
      DialogConfirmComponent,
      {
        data: {
          text: `Do you want to delete ${this.nearbyPlaceSelected.name}?`,
          button1: 'Yes',
          button2: 'No',
        },
      },
      (result: any) => {
        if (result) {
          let index = this.nearbyPlacesView.findIndex(place => place.id == this.nearbyPlaceSelected.id);
          this.nearbyPlacesView.splice(index, 1);
        }
      }
    );
  }

  public selectNearbyPlace(place: NearbyPlace) {
    this.nearbyPlaceSelected = place;
  }
}
