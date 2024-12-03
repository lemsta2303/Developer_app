export class ProjectDetailsModel {
  id: string;
  name: string;
  location: string

  constructor(id: string, name: string, location: string) {
    this.id = id;
    this.name = name;
    this.location = location;
  }
}
