import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ActionService {
  constructor() {}

  private subjectMenuBar = new Subject<string>();
  actionUpdateState = this.subjectMenuBar.asObservable();
  updateMenuState(action: string) {
    this.subjectMenuBar.next(action);
  }
}
