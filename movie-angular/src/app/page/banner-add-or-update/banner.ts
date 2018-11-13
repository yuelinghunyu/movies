export class Banner{
    constructor(
        public id:string="",
        public title:string="",
        public type:number = 1,
        public href:string="",
        public redirct:string="",
        public createTime:string=""
    ){}
    public static bannerDefault = new Banner("","",1,"","","");
}