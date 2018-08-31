export class Person{
    constructor(
        public id:string="",
        public userName:string="",
        public passWord:string="",
        public img:string="",
        public createDate:Date
    ){ }
    public static personDefault = new Person("","","","",null);
}