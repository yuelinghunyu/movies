export class Movie{
    constructor(
        public id:string="",
        public area:number=-1,
        public picUrl:string="",
        public content:string="",
        public description:string="",
        public title:string="",
        public type:number=-1,
        public price:number=-1,
        public count:number=-1,
        public movieType:number=-1,
        public isFree:number=1
    ){}
    public static movieDefault = new Movie("",-1,"","","","",-1,-1,-1,-1,1);
}