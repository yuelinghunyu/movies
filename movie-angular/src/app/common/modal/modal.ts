export class Modal{
    constructor(
      public tips:string = "",
      public id:string = "",
      public flag:boolean = false,
    ){}
    changeEvent(id:string){

    }
    public static modal = new Modal("");
}