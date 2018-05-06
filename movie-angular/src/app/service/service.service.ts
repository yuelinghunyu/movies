import { Injectable } from '@angular/core';
import {Http, RequestOptions,Headers} from "@angular/http";

@Injectable()
export class ServiceService {

  constructor(public http:Http) { }

  //个人中心
  getPersonList(){
    const url =  "/movies/person/list";
    return this.http.get(url);
  }
}
