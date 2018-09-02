import { Injectable } from '@angular/core';
import { HttpEvent,HttpInterceptor,HttpHandler,HttpRequest,HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { mergeMap } from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable()
export class InterceptorService implements HttpInterceptor{
    constructor(
        private router:Router,
    ){ }
    authorization:string = "";
    authReq:any;
    intercept(req:HttpRequest<any>,next:HttpHandler):Observable<HttpEvent<any>>{
        this.authorization = "mso " + localStorage.getItem("accessToken");
        
        if (req.url.indexOf('/person/exsit') === -1) {
            this.authReq = req.clone({
                url:req.url,
                headers:req.headers.set("Authorization",this.authorization)
            });
        }else{
            this.authReq = req.clone({
                url:req.url,
            });
        }
        return next.handle(this.authReq).pipe(mergeMap((event:any) => {
            if(event instanceof HttpResponse && event.body === null){
                return this.handleData(event);
            }
            return Observable.create(observer => observer.next(event));
        }));
    }
    private handleData(event: HttpResponse<any>): Observable<any> {
        // 业务处理：一些通用操作
        switch (event.status) {
          case 200:
            if (event instanceof HttpResponse) {
                const body: any = event.body;
                if (body === null) {
                    this.backForLoginOut();
                }
            }
            break;
          case 401: // 未登录状态码
            this.backForLoginOut();
            break;
          case 404:
          case 500:
          break;
          default:
          return ErrorObservable.create(event);
      }
    }
    private backForLoginOut(){
        if(localStorage.getItem("accessToken") !== null || localStorage.getItem("person")!== null){
            localStorage.removeItem("accessToken");
            localStorage.removeItem("person");
        }
            if(localStorage.getItem("accessToken") === null && localStorage.getItem("person") === null){
            this.router.navigateByUrl('/login');
        }
    }
}
