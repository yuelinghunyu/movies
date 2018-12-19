import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';

@Component({
  selector: 'app-banner-list',
  templateUrl: './banner-list.component.html',
  styleUrls: ['./banner-list.component.css']
})
export class BannerListComponent implements OnInit {

  public tabShow:Boolean = true;
  public Config:Config = new Config();
  public bannerOneList:any = [];
  public bannerTwoList:any = [];
  public modal:Modal = Modal.modal;
  public type: any = 1;
  public total1:any = 0;
  public total2:any = 0;
  public state:string;
  public pagination1:Pagination = Pagination.defaultPagination;
  public pagination2:Pagination = Pagination.defaultPagination;
  constructor(
    public service:ServiceService,
    public router:Router
  ) { }

  ngOnInit() {
    this.state = 'inactive';
    const param1 = {
      "type":1,
      "page":this.pagination1.currentPage,
      "limit":this.pagination1.pageItems
    }
    const param2 = {
      "type":2,
      "page":this.pagination1.currentPage,
      "limit":this.pagination1.pageItems
    }
    this.getBannerList(param1)
    this.getBannerList(param2)
    this.pagination1.changePage = (()=>{
      const param1 = {
        "type":1,
        "page":this.pagination1.currentPage,
        "limit":this.pagination1.pageItems
      }
      this.getBannerList(param1)
    });
    this.pagination2.changePage = (()=>{
      const param2 = {
        "type":2,
        "page":this.pagination2.currentPage,
        "limit":this.pagination2.pageItems
      }
      this.getBannerList(param2)
    });
   
  }

  tabEvent(ev){
    const spans = ev.target.parentNode.children
    for(let i=0;i<spans.length;i++){
      spans[i].setAttribute('class','')
    }
    ev.target.setAttribute('class','span-tab-active')
    this.type = ev.target.getAttribute('type-name')
    
    this.tabShow = !this.tabShow
  }
  getBannerList(param){
    this.service.getBannerList(param).subscribe(res=>{
      if(res["code"] === this.Config.ERROR_OK){
       
        if(res['data'].type === 1){
          this.bannerOneList = res['data'].list;
          this.total1 = res['data'].total;
          this.pagination1.totalItems = this.total1;
        }else{
          this.bannerTwoList = res['data'].list;
          this.total2 = res['data'].total;
          this.pagination2.totalItems = this.total2;
        }
      }
    })
  }
  addPanel(){
    this.router.navigateByUrl("/frame/banner-add-update")
  }
   //弹框出现;
   public alertModalItem(id:string){
    this.modal.tips = "是否删除该项？";
    this.modal.id = id;
    this.modal.changeEvent=((id:string)=>{
      this.deleteItem(id);
    })
  }
  deleteItem(id:string){
    this.modal.flag = true;
    this.modal.tips = "正在删除，请稍后...";
    const body = {
      id:id
    }
    this.service.deleteBanner(body).subscribe(res=>{
      if(res['code'] === this.Config.ERROR_OK){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        if(res['data'].type === 1){
          const param1 = {
            "type":1,
            "page":this.pagination1.currentPage,
            "limit":this.pagination1.pageItems
          }
          this.getBannerList(param1)
        }else{
          const param2 = {
            "type":2,
            "page":this.pagination1.currentPage,
            "limit":this.pagination1.pageItems
          }
          this.getBannerList(param2)
        }
      }
    })
  }
}
