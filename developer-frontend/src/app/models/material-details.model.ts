export class MaterialDetailsModel {
  id: string;
  name: string;
  unit: string;
  unitPrice: number;
  quantity: number;


  constructor(id: string, name: string, unity: string, unitPrice:number, quantity:number) {
    this.id = id;
    this.name = name;
    this.unit = unity;
    this.unitPrice = unitPrice;
    this.quantity = quantity;

  }
}
