export class Blog{
    constructor(
        public id:string = "",
        public title:string = "",
        public blogType:number = null,
        public blogTypeTitle:string = "",
        public href:string = "",
        public times:number = null
    ){}
    public static defaultBlog = new Blog("","",null,"","",null)
}