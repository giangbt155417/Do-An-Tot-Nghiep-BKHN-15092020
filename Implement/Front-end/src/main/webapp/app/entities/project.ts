export class Project {
  id?: Number;
  name?: string;
  owner?: string;
  createdDate?: string;
  expiryDate?: string;
  description?: string;

  constructor(name?: string, owner?: string, createdDate?: string, expiryDate?: string, description?: string) {
    this.name = name;
    this.owner = owner;
    this.createdDate = createdDate;
    this.expiryDate = expiryDate;
    this.description = description;
  }
}
