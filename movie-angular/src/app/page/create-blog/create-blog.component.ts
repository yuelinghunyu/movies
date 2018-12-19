import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Pagination } from "../../common/pagination/pagination";
import { Modal } from '../../common/modal/modal';
import { Blog } from './blog';
import { Config } from '../../config/config';
import { ServiceService } from '../../service/service.service';

@Component({
  selector: 'app-create-blog',
  templateUrl: './create-blog.component.html',
  styleUrls: ['./create-blog.component.css']
})
export class CreateBlogComponent implements OnInit {

  @Output()
  public pagination:Pagination = Pagination.defaultPagination;
  public modal:Modal = Modal.modal;
  public blog:Blog = Blog.defaultBlog;
  public config:Config = new Config();
  public blogList=[]

  constructor(
    public service:ServiceService,
    public router:Router,
  ) {}

  ngOnInit() {
    this.initList();
    this.pagination.changePage = (()=>{
      this.initList();
    });
  }
  initList(){
    const param = {
      "page":this.pagination.currentPage,
      "limit":this.pagination.pageItems
    }
    this.service.getBlogList(param).subscribe(res=>{
      if(res["code"] === this.config.ERROR_OK){
        const data = res["data"];
        this.blogList = data.list;
        this.pagination.totalItems = data.total;
      }
    });
  }
  addBlog(){
    this.router.navigateByUrl("/frame/add-blog");
  }
  alertModalItem(id:string){
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
    this.service.deleteBlog(body).subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        this.modal.tips = "删除成功！";
        $("#tipModal").modal('hide');
        this.modal.flag = false;
        this.initList();
      }
    })
  }
}
