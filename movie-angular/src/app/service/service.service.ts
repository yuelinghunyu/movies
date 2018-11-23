import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/forkJoin';

@Injectable()
export class ServiceService {
  mso:string;
  httpOptions:Object;
  constructor(public http:HttpClient) {
    this.mso = "/mso";
    this.httpOptions = {
      headers:new HttpHeaders({
        'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8',
      }),
    }
  }
  /**登录模块开始*/
  loginMso(body){
    const url = this.mso+"/person/exsit";
    const param = 'userName='+body.userName+"&passWord="+body.password;
    return this.http.post(url,param,this.httpOptions);
  }
  /**登录模块结束*/
  /**首页模块开始 */
  //首页;
  getPersonItem(param){
    const url = this.mso+"/person/item";
    return this.http.get(url,{params:param});
  }
  //个人中心
  getPersonList(){
    const url =  this.mso+"/person/list";
    return this.http.get(url);
  }
  /**首页模块结束 */


  /**地域模块开始 */
  //创建地域
  createAreaItem(body){
    const url =  this.mso+"/areas/addOrUpdate";
    const param = 'id='+body.id+'&area='+body.area+'&title='+body.title;
    return this.http.post(url,param,this.httpOptions);
  }
  //获取地域列表
  getAreasList(param){
    const url = this.mso+"/areas/list";
    return this.http.get(url,{params:param});
  }
  //删除一条地域记录
  deleteAreaOne(param){
    const url =  this.mso+"/areas/delete";
    const id = 'id='+param.id;
    return this.http.post(url,id,this.httpOptions);
  }
  //获取一条地域记录
  getAreaOne(param){
    const url = this.mso+"/areas/getItem";
    return this.http.get(url,{params:param});
  }
  //获取地域总数
  getAreaTotal():Observable<Object>{
    const url =  this.mso+"/areas/getTotal";
    return this.http.get(url).map(res=>{return res});
  }
  /**地域模块结束 */

  /**类型模块开始 */
  //获取类型列表
  getTypesList(param){
    const url = this.mso+"/types/list";
    return this.http.get(url,{params:param});
  }
  //删除一条类型记录
  deleteTypeOne(param){
    const url =  this.mso+"/types/delete";
    const id = 'id='+param.id;
    return this.http.post(url,id,this.httpOptions);
  }
  //获取一条类型记录
  getTypeOne(param){
    const url = this.mso+"/types/getItem";
    return this.http.get(url,{params:param});
  }
  //获取类型总数
  getTypeTotal():Observable<Object>{
    const url =  this.mso+"/types/getTotal";
    return this.http.get(url).map(res=>{return res});
  }
  //创建类型
  createTypeItem(body){
    const url =  this.mso+"/types/addOrUpdate";
    const param = 'id='+body.id+'&type='+body.type+'&title='+body.title;
    return this.http.post(url,param,this.httpOptions);
  }
  /**类型模块结束 */

  /**电影模块开始 */
  //合并同时加载
  getForkJoin():Observable<any>{
    return Observable.forkJoin(
        this.getAreaTotal(),
        this.getTypeTotal()           
    )
  }
  getForkJoinList(param1,param2){
    return Observable.forkJoin(
      this.getAreasList(param1),
      this.getTypesList(param2)           
    )
  }
  //创建单个电影
  createMovieItem(body){
    const url = this.mso+"/movie/addOrUpdate";
    const param = 
          "id="+body.id+
          "&area="+body.area+
          "&picUrl="+body.picUrl+
          "&content="+body.content+
          "&description="+body.description+
          "&title="+body.title+
          "&actor="+body.actor+
          "&type="+body.type+
          "&price="+body.price+
          "&movieType="+body.movieType+
          "&isFree="+body.isFree;
    console.log(body.picUrl);
    return this.http.post(url,param,this.httpOptions);
  }
  //查询影片列表
  getMovieList(param){
    const url = this.mso + "/movie/list"
    return this.http.get(url,{params:param});
  }
  //模糊查询影片列表
  getMovieLikeList(param){
    const url = this.mso + "/movie/listLike"
    return this.http.get(url,{params:param});
  }
  //删除一条电影记录
  deleteMovieItem(body){
    const url =  this.mso+"/movie/delete";
    const param = 'id='+body.id;
    return this.http.post(url,param,this.httpOptions);
  }
  /**电影模块结束 */
  

  /**小册接口开始 */
  // 小册类型获取
  getBookTypeList(param){
    const url = this.mso + "/bookType/list";
    return this.http.get(url,{params:param})
  }
  createBookType(body){
    const url = this.mso + "/bookType/addOrUpdate"
    const param = 'id=' + body.id + '&typeId=' + body.typeId + '&typeTitle=' + body.typeTitle
    return this.http.post(url,param,this.httpOptions);
  }
  deleteBookType(body){
    const url = this.mso + "/bookType/delete";
    const param = 'id=' + body.id;
    return this.http.post(url,param,this.httpOptions);
  }

  //小册博客获取
  setBlog(body){
    const url = this.mso + "/blog/addOrUpdate"
    const param = 'id='+body.id+'&title='+body.title+'&blogType='+body.blogType+'&href='+body.href
    return this.http.post(url,param,this.httpOptions)
  }
  getBlogList(param){
    const url = this.mso + "/blog/list";
    return this.http.get(url,{params:param})
  }
  deleteBlog(body){
    const url = this.mso + "/blog/delete";
    const param = 'id=' + body.id;
    return this.http.post(url,param,this.httpOptions);
  }
  /**小册接口结束 */

  /**轮播图列表开始*/
  getBannerList(param){
    const url = this.mso + '/banner/list';
    return this.http.get(url,{params:param})
  }
  setBanner(body){
    const url = this.mso + '/banner/addOrUpdate';
    const param = 'id='+body.id+"&title="+body.title+"&type="+body.type+"&href="+body.href+"&redirect="+body.redirect;
    return this.http.post(url,param,this.httpOptions);
  }
  deleteBanner(body){
    const url = this.mso + "/banner/delete";
    const param = 'id=' + body.id;
    return this.http.post(url,param,this.httpOptions);
  }
  /**轮播图列表结束*/

}
