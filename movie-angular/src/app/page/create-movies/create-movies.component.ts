import { Component, OnInit, Output} from '@angular/core';
import { Router } from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/forkJoin';

import { ServiceService } from '../../service/service.service';
import { MovieType } from './movieType';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Config } from "../../config/config";

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
  Config:Config = new Config();
  //最新、最热、经典
  movieTypeList:Array<any>;

  total:number;

  //选框插叙参数;
  movieListBySelect:Object = {
      area:-1,
      type:-1,
      movieType:-1
  };
  constructor(
    private router:Router,
    private service:ServiceService
  ) {}

  ngOnInit() {
    let  newArray = [];
    let  item0 = new MovieType(-1,"分类");
    let  item1 = new MovieType(1,"最热");
    let  item2 = new MovieType(2,"经典");
    let  item3 = new MovieType(3,"最新");

    newArray.push(item0);
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
          let areaItem = {id:"all",area:-1,title:"区域"};
          this.areaList.unshift(areaItem);
          console.log(this.areaList);
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
          let typeItem = {id:"all",type:-1,title:"类型"};
          this.typeList.unshift(typeItem);
          console.log(this.typeList);
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
    this.getMovieListBySelect(param);
  }
   //弹框出现;
   private alertModalItem(id:string){
    this.modal.tips = "是否删除该项？";
    this.modal.id = id;
    this.modal.changeEvent=((id:string)=>{
      this.deleteTypeItem(id);
    })
  }
  //删除一条记录;
  private deleteTypeItem(id:string){
    this.modal.flag = true;
    this.modal.tips = "正在删除，请稍后...";
    const param = {
      "id":id
    }
    this.service.deleteMovieItem(param).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        this.initList();
      }
    })
  }
  //跳转到新增影片面板;
  addMovieUrl(){
    this.router.navigateByUrl("/frame/add-movie");
  }

  areaEvent(title:string,area:any){
    this.movieAreaTitle = title;
    this.movieListBySelect["area"] = area;
    console.log(area);
    this.getMovieListBySelect(this.movieListBySelect);
  }
  typeEvent(title:string,type:any){
    this.movieTypeTitle = title;
    this.movieListBySelect["type"] = type;
    console.log(type);
    this.getMovieListBySelect(this.movieListBySelect);
  }
  movieTypeEvent(title:string,type:any){
    this.movieTypeType = title;
    this.movieListBySelect["movieType"] = type;
    console.log(type);
    this.getMovieListBySelect(this.movieListBySelect);
  }
  //获取接口数据，并更新列表渲染数据;
  getMovieListBySelect(param){
    this.service.getMovieList(param).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
        const data = res["data"]
        this.movieList = data.list;
        this.total = data.total;
        this.pagination.totalItems = this.total;
        
        //清除searchVal
        document.getElementById("movie-name")["value"] = "";
      }
    })
  }
  //模糊查询电影;
  searchMovie(){
    const searchVal = document.getElementById("movie-name")["value"];
    if(searchVal !== ""){
      const param = {
        title:searchVal
      }
      this.service.getMovieLikeList(param).subscribe(res=>{
        if(res["code"] === this.Config.ERROR_OK){
          const data = res["data"]
          this.movieList = data.list;
          this.total = data.total;
          this.pagination.totalItems = this.total;

          //这里面要清除this.movieListBySelect和恢复下拉框默认值
          this.movieListBySelect = {
            area:-1,
            type:-1,
            movieType:-1
          }
          this.movieAreaTitle = "区域";
          this.movieTypeTitle = "类型";
          this.movieTypeType = "分类";
        }
      })
    }
  }
  //清空搜索框调用函数;
  blankMovie(ev:any){
    if(ev.target.value === ""){
      const param = {
        "page":this.pagination.currentPage,
        "limit":this.pagination.pageItems
      }
      this.service.getMovieList(param).subscribe(res=>{
        if(res["code"] === this.Config.ERROR_OK){
          const data = res["data"]
          this.movieList = data.list;
          this.total = data.total;
          this.pagination.totalItems = this.total;
          
          //清除searchVal
          document.getElementById("movie-name")["value"] = "";
          this.movieListBySelect = {
            area:-1,
            type:-1,
            movieType:-1
          }
          this.movieAreaTitle = "区域";
          this.movieTypeTitle = "类型";
          this.movieTypeType = "分类";
        }
      });
    }
  }
}
