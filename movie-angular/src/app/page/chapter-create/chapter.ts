export class Chapter{
    constructor(
        public id:string="",
        public bookId:string="",
        public bookTitle:string="",
        public title:string="",
        public href:string="",
        public time:string="",
    ){}
    public static chapterDefault = new Chapter("","","","","","");
}