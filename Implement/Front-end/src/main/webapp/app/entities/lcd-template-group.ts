export class LCDTemplateGroup {
  id?: Number;
  name?: string;
  width?: number;
  height?: number;
  owner?: string;
  createdDate?: string;
  projectId?: Number;

  constructor(name?: string, width?: number, height?: number, owner?: string, createdDate?: string, projectId?: Number) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.owner = owner;
    this.createdDate = createdDate;
    this.projectId = projectId;
  }
}
