import { Component, Input, DoCheck } from '@angular/core';
import { Pagination } from "./pagination";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements DoCheck {

  @Input()
  public pagination:Pagination;
  public pageNum:number;
  public pageList:any[];

  public oldTotalItems:number = 0;

  // 改变分页页；
  public changeCurrentPage(item:any):void{
    if(typeof item === 'number'){
      this.pagination.currentPage = item;
      this.pagination.changePage();
    }
  }

  //上一页
  public prePage():void{
    if(this.pagination.currentPage != 1){
      this.changeCurrentPage(this.pagination.currentPage - 1);
    }
  }

  //下一页
  public nextPage():void{
    if(this.pagination.currentPage <　this.pageNum){
      this.changeCurrentPage(this.pagination.currentPage + 1);
    }
  }

  public initPageList():void{
    //偏移量（因为除去首页和尾页，所以要-1）
    let offset = Math.floor(this.pagination.pageLength / 2) - 1;
    //如果没有数据显示第一页;
    this.pagination.totalItems = this.pagination.totalItems > 0 ? this.pagination.totalItems : 1;
    //总页数
    this.pageNum = Math.ceil(this.pagination.totalItems / this.pagination.pageItems);
    this.pageList = [];
    if(this.pageNum <= this.pagination.pageLength){
      for(let i=1;i<=this.pageNum;i++){
        this.pageList.push(i);
      }
    }else{
      //左边没有"...."
      if(this.pagination.currentPage < this.pagination.pageLength - offset){
        for(let i=1;i<this.pagination.pageLength;i++){
          this.pageList.push(i);
        }
        this.pageList.push("...");
        this.pageList.push(this.pageNum);
        //右边没有"..."
      }else if(this.pagination.currentPage >= this.pageNum - offset -1){
        this.pageList.push(1);
        this.pageList.push("...");
        for(let i = this.pagination.pageLength -2;i>=0;i--){
          this.pageList.push(this.pageNum - i)
        }
        //两边都有"..."
      }else{
        this.pageList.push(1);
        this.pageList.push("...");
        for(
          let i= this.pagination.currentPage - offset;
          i <= this.pagination.currentPage + offset;
          i++
        ){
          this.pageList.push(i);
        }
        this.pageList.push("...");
        this.pageList.push(this.pageNum);
      }
    }
  }
  ngDoCheck():void{
    if(this.pagination.totalItems != this.oldTotalItems){
      this.initPageList();
      this.oldTotalItems = this.pagination.totalItems;
    }
    if(this.pagination.currentPage > this.pageNum){
      this.pagination.currentPage = this.pageNum;
      this.pagination.changePage();
    }
  }
}
