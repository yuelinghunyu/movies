export class BookType{
    constructor(
        public id:string = "",
        public bookType:number = null,
        public bookTitle:string = ""
    ){

    }
    public static defaultBookType = new BookType("",null,"")
}