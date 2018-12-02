import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.css']
})
export class BookCreateComponent implements OnInit {
  public bookCreateForm:FormGroup;
  private config:Config = new Config();
  public titleFlag:Boolean = false;
  public authorFlag:Boolean = false;
  public bookLogoFlag:Boolean = false;
  public bookDescriptionFlag:Boolean = false;
  private authorization:string;

  uploader:FileUploader = new FileUploader({
    url:"/mso/file/toOssServer",
    method:"post",
    headers:[{name:'Authorization',value:this.authorization = "mso " + localStorage.getItem("accessToken")}],
    itemAlias:"uploader",
    autoUpload:false
  });

  constructor(
    private fb: FormBuilder,
    private service:ServiceService,
    private router:Router
  ) { 
    this.createBookForm()
  }

  ngOnInit() {

  }
  createBookForm(){
    this.bookCreateForm = this.fb.group({
      bookTitle:["",[Validators.required]],
      bookLogo:["",[Validators.required]],
      bookAuthor:["",[Validators.required]],
      bookIntroUrl:["",[Validators.required]],
      bookType:["",[Validators.required]],
      bookPrice:[""],
      bookDescription:["",[Validators.required]]
    })
  }
  createBook(){
    const formData = this.bookCreateForm.value;
    console.log(formData)
  }
}
