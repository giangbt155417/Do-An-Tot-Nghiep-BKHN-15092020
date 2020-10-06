export class LCDTemplate {
  id?: Number;
  name?: string;
  width?: number;
  height?: number;
  createdDate?: string;
  contentGroupId?: Number;

  constructor(name?: string, width?: number, height?: number, createdDate?: string, contentGroupId?: Number) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.createdDate = createdDate;
    this.contentGroupId = contentGroupId;
  }
}
