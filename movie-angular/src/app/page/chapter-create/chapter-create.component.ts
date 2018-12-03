import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup,Validators} from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { ServiceService } from '../../service/service.service';
import { Router } from '@angular/router';
import { Config} from "../../config/config";

@Component({
  selector: 'app-chapter-create',
  templateUrl: './chapter-create.component.html',
  styleUrls: ['./chapter-create.component.css']
})
export class ChapterCreateComponent implements OnInit {
  public chapterCreateForm:FormGroup;
  private config:Config = new Config();
  public titleFlag:Boolean = false;
  public bookFlag:Boolean = false;
  public linkFlag:Boolean = false;
  public timeFlag:Boolean = false;
  public authorization:string;

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
  }

  createChapter(){
    const formData = this.chapterCreateForm.value;
    console.log(formData)
  }

}
