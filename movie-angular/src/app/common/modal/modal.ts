export class Modal{
    constructor(
      public tips:string = "",
      public id:string = "",
      public flag:boolean = false,
      public close:boolean = false,
    ){}
    changeEvent(id:string){

    }
    closeEvent(){

    }
    public static modal = new Modal("");
}