export class Type{
    constructor(
        public id:string = "",
        public tp:number = null,
        public title:string = ""
    ){

    }
    public static defaultType = new Type("",null,"")
}