export class MediaFolder {
  id?: Number;
  name?: string;
  createDate?: Date;

  constructor(id?: Number, name?: string, createDate?: Date) {
    this.id = id;
    this.name = name;
    this.createDate = createDate;
  }
}
