import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";
import { Book } from './book';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.css']
})
export class BookCreateComponent implements OnInit {
  public bookCreateForm:FormGroup;
  public config:Config = new Config();
  public book:Book = Book.bookDefault;
  public titleFlag:Boolean = false;
  public authorFlag:Boolean = false;
  public bookLogoFlag:Boolean = false;
  public bookDescriptionFlag:Boolean = false;
  public bookIntroUrlFlag:Boolean = false;
  public typeFlag:Boolean = false;
  public authorization:string;
  public hrefLink:Boolean = false;
  public introLink:Boolean = false;
  public jpgValid:boolean=false;
  public bookTypeVal: string= '博客类型'

  public bookTypeList:[]

  uploader:FileUploader = new FileUploader({
    url:"/mso/file/toOssServer",
    method:"post",
    headers:[{name:'Authorization',value:this.authorization = "mso " + localStorage.getItem("accessToken")}],
    itemAlias:"uploader",
    autoUpload:false,
  });

  constructor(
    private fb: FormBuilder,
    private service:ServiceService,
    private router:Router
  ) { 
    this.createBookForm()
  }

  ngOnInit() {
    this.getBookTypes()
  }
  getBookTypes(){
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
    if(formData.bookTitle === ""){
      this.titleFlag = true;
      return false;
    }
    if(formData.bookLogo === ""){
      this.bookLogoFlag = true;
      return false;
    }
    if(formData.bookAuthor === ""){
      this.authorFlag = true;
      return false;
    }
    if(formData.bookIntroUrl === ""){
      this.bookIntroUrlFlag = true;
      return false
    }
    if(formData.bookType === ""){
      this.typeFlag = true;
      return false;
    }
    if(formData.bookDescription === ""){
      this.bookDescriptionFlag = true;
      return false;
    }
    const param = {
      id:"",
      title:formData.bookTitle,
      logo:formData.bookLogo,
      introUrl:formData.bookIntroUrl,
      author:formData.bookAuthor,
      bookType:formData.bookType,
      price:formData.bookPrice,
      description:formData.bookDescription
    }
    console.log(param)
    this.service.setBook(param).subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        this.bookCreateForm.reset()
        this.router.navigateByUrl("/frame/book-list");
      }
    })
  }
  fileChange(ev){
    const isJpg = ev.target.files[0].name.split(".")[1];
    const exg = "(png|jpg|gif|jpeg|PNG|JPG|GIF|JPEG)";
    this.jpgValid = new RegExp(exg).test(isJpg);
    if(this.jpgValid){
      let lg = this.uploader.queue.length;
      this.uploader.queue[lg-1].upload();//开始上传;
      this.uploader.queue[lg-1].onSuccess = (res,status,headers)=>{
        if(status == 200){
           const resData = JSON.parse(res);
           this.book.logo = resData["data"].fileUrl;
           this.hrefLink = true;
        }
      }
      this.uploader.queue[lg-1].onError =(res,status,headers)=>{}
    }
  }
  introChange(ev){
    let lg = this.uploader.queue.length;
    this.uploader.queue[lg-1].upload();//开始上传;
    this.uploader.queue[lg-1].onSuccess = (res,status,headers)=>{
      if(status == 200){
          const resData = JSON.parse(res);
          this.book.introUrl = resData["data"].fileUrl;
          this.introLink = true;
      }
    }
    this.uploader.queue[lg-1].onError =(res,status,headers)=>{}
  }
  selectType(ev:Event){
    this.bookTypeVal = ev.target['textContent'];
    this.book.bookType = ev.target['getAttribute']("type")
  }
}
