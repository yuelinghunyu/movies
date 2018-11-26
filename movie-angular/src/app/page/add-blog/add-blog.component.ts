import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";
import { Blog } from '../create-blog/blog';

@Component({
  selector: 'app-add-blog',
  templateUrl: './add-blog.component.html',
  styleUrls: ['./add-blog.component.css']
})
export class AddBlogComponent implements OnInit {
  public blogForm:FormGroup
  public blog:Blog = Blog.defaultBlog;
  public titleFlag:Boolean = false;
  public hrefFlag:Boolean = false;
  public typeFlag:Boolean = false;
  private config:Config = new Config();
  private bookTypeList = [];
  private blogTypeVal = '博客类型'
  constructor(
    private fb: FormBuilder,
    private service:ServiceService,
    private router:Router
  ) { 
    this.createBlogForm()
  }

  ngOnInit() {
    this.initBookTypeList()
  }
  initBookTypeList(){
    this.service.getBookTypeTotal().subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        const total = res['data']
        this.service.getBookTypeList({page:1,limit:total}).subscribe(res=>{
          if(res['code'] === this.config.ERROR_OK){
            this.bookTypeList = res['data'].list
          }
        })
      }
    })
  }
  createBlogForm(){
    this.blogForm = this.fb.group({
      blogTitle:["",[Validators.required]],
      blogType:["",[Validators.required]],
      blogHref:["",[Validators.required]]
    })
  }
  createBlog(){
    const formData = this.blogForm.value;
    if(formData.blogTitle === ""){
      this.titleFlag = true;
      return false;
    }
    if(formData.blogType === null){
      this.typeFlag = true;
      return false;
    }
    if(formData.blogHref === ""){
      this.hrefFlag = true
      return false;
    }
    const param = {
      id:"",
      title:formData.blogTitle,
      blogType:formData.blogType,
      href:formData.blogHref
    }
    this.service.setBlog(param).subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        this.blogForm.reset()
        this.router.navigateByUrl("/frame/create-blog");
      }
    })
  }
  selectType(ev:Event){
    this.blogTypeVal = ev.target['textContent'];
    this.blog.blogType = ev.target['getAttribute']("type")
  }

}
