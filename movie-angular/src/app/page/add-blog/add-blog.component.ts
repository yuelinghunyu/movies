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
  public hrefFlag:Boolean = false
  constructor(
    private fb: FormBuilder,
    private service:ServiceService,
    private router:Router
  ) { 
    this.createBlogForm()
  }

  ngOnInit() {
  }

  createBlogForm(){
    this.blogForm = this.fb.group({
      blogTitle:["",[Validators.required]],
      blogType:["",[Validators.required]],
      blogHref:["",[Validators.required]]
    })
  }
  createBlog(){

  }
  selectType(ev:Event){

  }

}
