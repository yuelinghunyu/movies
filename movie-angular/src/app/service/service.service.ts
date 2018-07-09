import { Injectable } from '@angular/core';
import {Http, RequestOptions,Headers} from "@angular/http";
import { HttpHeaders } from "@angular/common/http";

@Injectable()
export class ServiceService {
  movies:string;
  httpOptions:Object;
  constructor(public http:Http) {
    this.movies = "/movies";
    this.httpOptions = {
      headers:new Headers({
        'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8',
      })
    }
   }

  /**首页模块开始 */
  //个人中心
  getPersonList(){
    const url =  this.movies+"/person/list";
    return this.http.get(url);
  }
  /**首页模块结束 */


  /**地域模块开始 */
  //创建地域
  createAreaItem(body){
    const url =  this.movies+"/areas/addOrUpdate";
    const param = 'id='+body.id+'&area='+body.area+'&title='+body.title;
    return this.http.post(url,param,this.httpOptions);
  }
  //获取地域列表
  getAreasList(param){
    const url = this.movies+"/areas/list";
    return this.http.get(url,{params:param});
  }
  //删除一条地域记录
  deleteAreaOne(param){
    const url =  this.movies+"/areas/delete";
    const id = 'id='+param.id;
    return this.http.post(url,id,this.httpOptions);
  }
  //获取一条地域记录
  getAreaOne(param){
    const url = this.movies+"/areas/getItem";
    return this.http.get(url,{params:param});
  }
  /**地域模块结束 */

  /**类型模块开始 */
  //获取类型列表
  getTypesList(param){
    const url = this.movies+"/types/list";
    return this.http.get(url,{params:param});
  }
  //删除一条类型记录
  deleteTypeOne(param){
    const url =  this.movies+"/types/delete";
    const id = 'id='+param.id;
    return this.http.post(url,id,this.httpOptions);
  }
  //获取一条类型记录
  getTypeOne(param){
    const url = this.movies+"/types/getItem";
    return this.http.get(url,{params:param});
  }
  //创建类型
  createTypeItem(body){
    const url =  this.movies+"/types/addOrUpdate";
    const param = 'id='+body.id+'&type='+body.type+'&title='+body.title;
    return this.http.post(url,param,this.httpOptions);
  }
  /**类型模块结束 */
}
