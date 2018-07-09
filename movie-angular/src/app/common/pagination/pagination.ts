export class Pagination{
    /**
     * 构造函数，同时设置属性，初始值
     * @param pageLength 显示页码数，奇数，默认7
     * @param currentPage 当前页码数，默认1
     * @param totalItems 总条数，默认0
     * @param pageItems 每页显示条数，默认5
     * @param changePage 翻页时调用的方法
     */
    constructor(
        public pageLength:number = 7,
        public currentPage:number = 1,
        public totalItems:number = 0,
        public pageItems:number = 5,
    ){}
    changePage(){

    }
    public static defaultPagination = new Pagination(7,1,0,5);
}