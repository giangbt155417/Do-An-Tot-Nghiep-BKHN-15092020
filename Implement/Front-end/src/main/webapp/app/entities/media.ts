export abstract class Media {
  id?: Number;
  name?: string;
  url?: string;
  folderId?: Number;

  constructor(id?: Number, name?: string, url?: string, folderId?: Number) {
    this.id = id;
    this.name = name;
    this.url = url;
    this.folderId = folderId;
  }
}
