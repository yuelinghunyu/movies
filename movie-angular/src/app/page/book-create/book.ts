export class Book{
    constructor(
        public id:string="",
        public title:string="",
        public logo:string="",
        public introUrl:string="",
        public author:string="",
        public bookType:number = 0,
        public price:string = "",
        public description:string = ""
    ){}
    public static bookDefault = new Book("","","","","",0,"","");
}