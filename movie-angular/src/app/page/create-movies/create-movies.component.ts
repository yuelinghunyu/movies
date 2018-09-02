import { Component, OnInit, Output} from '@angular/core';
import { Router } from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/forkJoin';

import { ServiceService } from '../../service/service.service';
import { MovieType } from './movieType';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';

@Component({
  selector: 'app-create-movies',
  templateUrl: './create-movies.component.html',
  styleUrls: ['./create-movies.component.css']
})
export class CreateMoviesComponent implements OnInit {
  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  //类型字段；
  movieAreaTitle:string="区域";
  movieTypeTitle:string="类型";
  movieTypeType:string="分类";
 
  areaList:Array<any>;
  typeList:Array<any>;
  movieList:Array<any>;

  //最新、最热、经典
  movieTypeList:Array<any>;

  total:number
  constructor(
    private router:Router,
    private service:ServiceService
  ) {
    
   }

  ngOnInit() {
    let  newArray = [];
    let  item1 = new MovieType(1,"最热");
    let  item2 = new MovieType(2,"经典");
    let  item3 = new MovieType(3,"最新");

    newArray.push(item1);
    newArray.push(item2);
    newArray.push(item3);

    this.movieTypeList = newArray;
     //并发请求地域、类型的total值;
    let areasCount = 0,typesCount=0;
    // this.service.getForkJoin().subscribe(([areas,types])=>{
    //     areasCount = areas["data"];
    //     typesCount = types["data"];

    //     let areaParam = {
    //       "page":1,
    //       "limit":areasCount
    //     };
    //     let typeParam = {
    //       "page":1,
    //       "limit":typesCount
    //     }
    //     let areaList = this.service.getAreasList(areaParam);
    //     let typeList = this.service.getTypesList(typeParam);
    //     this.service.getForkJoinList(areaParam,typeParam).subscribe(([areaList,typeList])=>{
    //       this.areaList = areaList["data"].list;
    //       this.typeList = typeList["data"].list;
    //     })
    // });
    //以上注释代码在添加拦截器后无效;
    $.when(this.service.getAreaTotal(),this.service.getTypeTotal()).done((res1,res2)=>{
      res1.subscribe(areas=>{
        areasCount = areas["data"];
        let areaParam = {
          "page":1,
          "limit":areasCount
        };
        let areaList = this.service.getAreasList(areaParam);
        areaList.subscribe(list=>{
          this.areaList = list["data"].list;
        });
      });
      res2.subscribe(types=>{
        typesCount = types["data"];
        let typeParam = {
          "page":1,
          "limit":typesCount
        }
        let typeList = this.service.getTypesList(typeParam);
        typeList.subscribe(list=>{
          this.typeList = list["data"].list;
        });
      });
    });

    this.initList();
    this.pagination.changePage = (()=>{
      this.initList();
    });
  }
  //获取电影列表
  initList(){
     //获取所有的movie列表;
     const param = {
      "page":this.pagination.currentPage,
      "limit":this.pagination.pageItems
    }
    this.service.getMovieList(param).subscribe(res=>{
      if(res["code"] === 0){
        const data = res["data"]
        this.movieList = data.list;
        this.total = data.total;
        this.pagination.totalItems = this.total;
      }
    })
  }
  //跳转到新增影片面板;
  addMovieUrl(){
    this.router.navigateByUrl("/add-movie");
  }

  areaEvent(title:string,area:any){
    this.movieAreaTitle = title;
    console.log(area);
  }
  typeEvent(title:string,type:any){
    this.movieTypeTitle = title;
    console.log(type);
  }
  movieTypeEvent(title:string,type:any){
    this.movieTypeType = title;
  }
}
