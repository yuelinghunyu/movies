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
  public config:Config = new Config();s

  constructor(
    public service:ServiceService,
    private router:Router,
  ) {}

  ngOnInit() {
  }
  addBlog(){
    this.router.navigateByUrl("/frame/add-blog");
  }
}
