export class Project {
  id?: Number;
  name?: string;
  owner?: string;
  createdDate?: string;

  constructor(name?: string, owner?: string, createdDate?: string) {
    this.name = name;
    this.owner = owner;
    this.createdDate = createdDate;
  }
}
