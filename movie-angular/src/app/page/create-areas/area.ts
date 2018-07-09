export class Area{
    constructor(
        public id:string = "",
        public area:number = null,
        public title:string = ""
    ){

    }
    public static defaultArea = new Area("",null,"")
}