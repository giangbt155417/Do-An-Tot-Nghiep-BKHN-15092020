export class Constants {
  static readonly RECORDS_PER_PAGE = 2;
  static readonly BACK_END_URL = 'http://localhost:8080/services/';
  static readonly PROGRAM_SERVICE = 'program/';
  static readonly MEDIA_SERVICE = 'media/';
}

export enum FIELD_COMPONENT {
  LoginModalComponent,
  ProjectManagementComponent,
  UserManagementComponent,
  RouteManagementComponent,
  NearbyPlacesManagementComponent,
  MediaManagementComponent,
  LcdContentEditorComponent,
  BusStopListManagementComponent,
}

export enum ContentEditorTools {
  SELECT_AREA,
  DRAW_FIX_TEXT,
  DRAW_LINK_TEXT,
  DRAW_FIX_PICTURE,
  DRAW_LINK_PICTURE,
  ZOOM_PAN,
  HORIZONTAL_ALIGN_LEFT,
  HORIZONTAL_ALIGN_CENTER,
  HORIZONTAL_ALIGN_RIGHT,
  VERTICAL_ALIGN_TOP,
  VERTICAL_ALIGN_CENTER,
  VERTICAL_ALIGN_BOTTOM,
}
