import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";
import { Chapter } from './chapter';

@Component({
  selector: 'app-chapter-create',
  templateUrl: './chapter-create.component.html',
  styleUrls: ['./chapter-create.component.css']
})
export class ChapterCreateComponent implements OnInit {
  public chapterCreateForm:FormGroup;
  public config:Config = new Config();
  public titleFlag:Boolean = false;
  public bookFlag:Boolean = false;
  public linkFlag:Boolean = false;
  public timeFlag:Boolean = false;

  public chapterHref:Boolean = false;
  public authorization:string;

  public bookList=[]
  public chapter:Chapter=Chapter.chapterDefault
  public chapterVal:string="章节小册"

  uploader:FileUploader = new FileUploader({
    url:"/mso/file/toOssServer",
    method:"post",
    headers:[{name:'Authorization',value:this.authorization = "mso " + localStorage.getItem("accessToken")}],
    itemAlias:"uploader",
    autoUpload:false
  });
  constructor(
    public fb: FormBuilder,
    public service:ServiceService,
    public router:Router
  ) {
    this.createChapterForm()
   }

   createChapterForm(){
    this.chapterCreateForm = this.fb.group({
      chapterTitle:["",[Validators.required]],
      chapterLink:["",[Validators.required]],
      chapterBook:["",[Validators.required]],
      chapterTime:["",[Validators.required]]
    })
  }
  ngOnInit() {
    this.getBooks()
  }
  getBooks(){
    this.service.getBookCount().subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        const total = res['data'].total
        this.service.getBookList({page:1,limit:total}).subscribe(res=>{
          if(res['code'] === this.config.ERROR_OK){
            this.bookList = res['data'].list
          }
        })
      }
    })
  }
  createChapter(){
    const formData = this.chapterCreateForm.value;
    if(formData.chapterTitle === ""){
      this.titleFlag = true;
      return false;
    }
    if(formData.chapterBook === ""){
      this.bookFlag = true;
      return false;
    }
    if(formData.chapterLink === ""){
      this.linkFlag = true;
      return false;
    }
    if(formData.chapterTime === ""){
      this.timeFlag = true;
      return false
    }
    const param = {
      id:"",
      bookId:formData.chapterBook,
      bookTitle:this.chapter.bookTitle,
      title:formData.chapterTitle,
      href:formData.chapterLink,
      time:formData.chapterTime,
    }
    console.log(param)
    this.service.setChapter(param).subscribe(res=>{
      if(res['code'] === this.config.ERROR_OK){
        this.chapterCreateForm.reset()
        this.router.navigateByUrl("/frame/chapter-list");
      }
    })
  }

  selectType(ev:Event){
    this.chapterVal = ev.target['textContent'];
    this.chapter.bookId = ev.target['getAttribute']("id")
    this.chapter.bookTitle = ev.target['getAttribute']("title")
  }

  fileChange(ev){
    let lg = this.uploader.queue.length;
    this.uploader.queue[lg-1].upload();//开始上传;
    this.uploader.queue[lg-1].onSuccess = (res,status,headers)=>{
      if(status == 200){
          const resData = JSON.parse(res);
          this.chapter.href = resData["data"].fileUrl;
          this.chapterHref = true;
      }
    }
    this.uploader.queue[lg-1].onError =(res,status,headers)=>{}
  }
}
