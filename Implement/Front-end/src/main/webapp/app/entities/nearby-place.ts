export class NearbyPlace {
  id?: Number;
  name?: string;
  picture?: any;
  description?: string;

  constructor(id?: Number, name?: string, picture?: any, description?: string) {
    this.id = id;
    this.name = name;
    this.picture = picture;
    this.description = description;
  }
}
